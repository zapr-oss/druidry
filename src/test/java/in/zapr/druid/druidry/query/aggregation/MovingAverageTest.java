package in.zapr.druid.druidry.query.aggregation;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Collections.singletonList;
import static org.testng.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import in.zapr.druid.druidry.aggregator.DoubleSumAggregator;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.averager.DoubleMaxAverager;
import in.zapr.druid.druidry.averager.DoubleMeanAverager;
import in.zapr.druid.druidry.averager.DoubleMeanNoNullsAverager;
import in.zapr.druid.druidry.averager.DoubleMinAverager;
import in.zapr.druid.druidry.averager.DoubleSumAverager;
import in.zapr.druid.druidry.averager.DruidAverager;
import in.zapr.druid.druidry.averager.LongMaxAverager;
import in.zapr.druid.druidry.averager.LongMeanAverager;
import in.zapr.druid.druidry.averager.LongMeanNoNullsAverager;
import in.zapr.druid.druidry.averager.LongMinAverager;
import in.zapr.druid.druidry.averager.LongSumAverager;
import in.zapr.druid.druidry.dataSource.TableDataSource;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.dimension.SimpleDimension;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.filter.havingSpec.EqualToHaving;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.limitSpec.DefaultLimitSpec;
import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpecString;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import in.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator;
import in.zapr.druid.druidry.query.config.Context;
import in.zapr.druid.druidry.query.config.Interval;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MovingAverageTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSimpleQuery() throws Exception {
        String expectedJson = loadExpectedJson("simple.json");

        DruidMovingAverageQuery query = simpleQuery(DoubleMeanAverager::new);

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFieldsQuery() throws Exception {
        String expectedJson = loadExpectedJson("allFields.json");
        DruidDimension druidDimension1 = new SimpleDimension("dimension_1");
        DruidDimension druidDimension2 = new SimpleDimension("dimension_2");
        DefaultLimitSpec limitSpec = new DefaultLimitSpec(5000, of(new OrderByColumnSpecString("column_1"),
                                                                   new OrderByColumnSpecString("column_2")));
        DateTime startTime = new DateTime(2020, 2, 1, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2020, 3, 31, 23, 59, 59, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);
        String averagedName = "averagedName";
        DruidAggregator aggregator = new DoubleSumAggregator(averagedName, "aggregatedFieldName");
        DruidPostAggregator postAggregator = new FieldAccessPostAggregator("postAggregatedFieldName");
        Context context = Context.builder().useCache(true).build();
        DruidAverager averager = new DoubleMeanAverager("averagingResult", averagedName, 60, 5);
        DruidPostAggregator postAverager = new FieldAccessPostAggregator("postAveragedFieldName");

        DruidMovingAverageQuery query = DruidMovingAverageQuery.builder()
                                                               .dataSource(new TableDataSource("allFieldsDataSource"))
                                                               .dimensions(of(druidDimension1, druidDimension2))
                                                               .limitSpec(limitSpec)
                                                               .having(new EqualToHaving("havingField", 5))
                                                               .granularity(new SimpleGranularity(PredefinedGranularity.FIFTEEN_MINUTE))
                                                               .filter(new SelectorFilter("selectorField", "selectorValue"))
                                                               .aggregations(singletonList(aggregator))
                                                               .postAggregations(singletonList(postAggregator))
                                                               .intervals(singletonList(interval))
                                                               .context(context)
                                                               .averagers(singletonList(averager))
                                                               .postAveragers(singletonList(postAverager))
                                                               .build();

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(dataProvider = "averagerTypesProvider")
    public void testAllAveragerTypes(String averagerName, TriFunction<String, String, Integer, DruidAverager> averagerCreator)
            throws Exception {
        String expectedJson = loadExpectedJsonForType(averagerName);

        DruidMovingAverageQuery query = simpleQuery(averagerCreator);

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @DataProvider(name = "averagerTypesProvider")
    private static Object[][] averagerTypesProvider() {
        return new Object[][] {
                { "doubleMax",         (TriFunction<String, String, Integer, DruidAverager>) DoubleMaxAverager::new },
                { "doubleMean",        (TriFunction<String, String, Integer, DruidAverager>) DoubleMeanAverager::new },
                { "doubleMeanNoNulls", (TriFunction<String, String, Integer, DruidAverager>) DoubleMeanNoNullsAverager::new },
                { "doubleMin",         (TriFunction<String, String, Integer, DruidAverager>) DoubleMinAverager::new },
                { "doubleSum",         (TriFunction<String, String, Integer, DruidAverager>) DoubleSumAverager::new },
                { "longMax",           (TriFunction<String, String, Integer, DruidAverager>) LongMaxAverager::new },
                { "longMean",          (TriFunction<String, String, Integer, DruidAverager>) LongMeanAverager::new },
                { "longMeanNoNulls",   (TriFunction<String, String, Integer, DruidAverager>) LongMeanNoNullsAverager::new },
                { "longMin",           (TriFunction<String, String, Integer, DruidAverager>) LongMinAverager::new },
                { "longSum",           (TriFunction<String, String, Integer, DruidAverager>) LongSumAverager::new }
        };
    }

    private String loadExpectedJsonForType(String type) throws IOException, URISyntaxException {
        String json = loadExpectedJson("averager_type/" + type + ".json");
        assertTrue(json.contains(String.format("\"type\": \"%s\"", type)));
        return json;
    }

    @SuppressWarnings("UnstableApiUsage")
    private String loadExpectedJson(String fileName) throws IOException, URISyntaxException {
        URI jsonUri = Resources.getResource("query/aggregation/moving_average/" + fileName).toURI();
        File jsonFile = new File(jsonUri);
        return Files.readFile(jsonFile);
    }

    private static DruidMovingAverageQuery simpleQuery(TriFunction<String, String, Integer, DruidAverager> averagerCreator) {
        DateTime startTime = new DateTime(2020, 1, 1, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2020, 2, 29, 23, 59, 59, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);
        String averagedName = "averagedName";
        DruidAggregator aggregator = new DoubleSumAggregator(averagedName, "aggregatedFieldName");
        DruidAverager averager = averagerCreator.apply("averagingResult", averagedName, 31);

        return DruidMovingAverageQuery.builder()
                                      .dataSource(new TableDataSource("simpleDataSource"))
                                      .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
                                      .aggregations(singletonList(aggregator))
                                      .intervals(singletonList(interval))
                                      .averagers(singletonList(averager))
                                      .build();
    }

    @FunctionalInterface
    private interface TriFunction<V1, V2, V3, T> {

        T apply(V1 v1, V2 v2, V3 v3);

    }

}
