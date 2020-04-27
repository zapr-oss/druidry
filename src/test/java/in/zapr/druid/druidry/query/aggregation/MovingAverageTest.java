/*
 * Copyright 2018-present Red Brick Lane Marketing Solutions Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.zapr.druid.druidry.query.aggregation;

import com.google.common.io.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import in.zapr.druid.druidry.aggregator.DoubleSumAggregator;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.averager.DoubleMeanAverager;
import in.zapr.druid.druidry.averager.DruidAverager;
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

import static com.google.common.collect.ImmutableList.of;
import static java.util.Collections.singletonList;
import static org.testng.Assert.assertTrue;

public class MovingAverageTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSimpleQuery() throws Exception {
        String expectedJson = loadExpectedJson("simple.json");

        DruidMovingAverageQuery query = simpleQuery(MovingAverageCreators::doubleMean);

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFieldsQuery() throws Exception {
        String expectedJson = loadExpectedJson("allFields.json");
        DruidDimension druidDimension1 = new SimpleDimension("dimension_1");
        DruidDimension druidDimension2 = new SimpleDimension("dimension_2");
        DefaultLimitSpec limitSpec =
                new DefaultLimitSpec(5000, of(new OrderByColumnSpecString("column_1"),
                        new OrderByColumnSpecString("column_2")));
        ZonedDateTime startTime = ZonedDateTime.of(2020, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2020, 3, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);
        String averagedName = "averagedName";
        DruidAggregator aggregator = new DoubleSumAggregator(averagedName, "aggregatedFieldName");
        DruidPostAggregator postAggregator = new FieldAccessPostAggregator("postAggregatedFieldName");
        Context context = Context.builder().useCache(true).build();
        DruidAverager averager = DoubleMeanAverager.builder()
                .name("averagingResult")
                .fieldName(averagedName)
                .buckets(60)
                .cycleSize(5)
                .build();
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

    @Test(expectedExceptions = NullPointerException.class)
    public void tryToBuildWithoutDataSource() {
        ZonedDateTime startTime = ZonedDateTime.of(2020, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2020, 3, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);
        DruidAggregator aggregator = new DoubleSumAggregator("name", "fieldName");
        DruidAverager averager = DoubleMeanAverager.builder()
                .name("name")
                .fieldName("fieldName")
                .buckets(60)
                .cycleSize(5)
                .build();

        DruidMovingAverageQuery.builder()
                .granularity(new SimpleGranularity(PredefinedGranularity.FIFTEEN_MINUTE))
                .intervals(singletonList(interval))
                .aggregations(singletonList(aggregator))
                .averagers(singletonList(averager))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void tryToBuildWithoutGranularity() {
        ZonedDateTime startTime = ZonedDateTime.of(2020, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2020, 3, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);
        DruidAggregator aggregator = new DoubleSumAggregator("name", "fieldName");
        DruidAverager averager = DoubleMeanAverager.builder()
                .name("name")
                .fieldName("fieldName")
                .buckets(60)
                .cycleSize(5)
                .build();

        DruidMovingAverageQuery.builder()
                .dataSource(new TableDataSource("dataSource"))
                .intervals(singletonList(interval))
                .aggregations(singletonList(aggregator))
                .averagers(singletonList(averager))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void tryToBuildWithoutAggregations() {
        ZonedDateTime startTime = ZonedDateTime.of(2020, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2020, 3, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);
        DruidAverager averager = DoubleMeanAverager.builder()
                .name("name")
                .fieldName("fieldName")
                .buckets(60)
                .cycleSize(5)
                .build();

        DruidMovingAverageQuery.builder()
                .dataSource(new TableDataSource("dataSource"))
                .granularity(new SimpleGranularity(PredefinedGranularity.FIFTEEN_MINUTE))
                .intervals(singletonList(interval))
                .averagers(singletonList(averager))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void tryToBuildWithoutIntervals() {
        DruidAggregator aggregator = new DoubleSumAggregator("name", "fieldName");
        DruidAverager averager = DoubleMeanAverager.builder()
                .name("name")
                .fieldName("fieldName")
                .buckets(60)
                .cycleSize(5)
                .build();

        DruidMovingAverageQuery.builder()
                .dataSource(new TableDataSource("dataSource"))
                .granularity(new SimpleGranularity(PredefinedGranularity.FIFTEEN_MINUTE))
                .aggregations(singletonList(aggregator))
                .averagers(singletonList(averager))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void tryToBuildWithoutAveragers() {
        ZonedDateTime startTime = ZonedDateTime.of(2020, 2, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2020, 3, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);
        DruidAggregator aggregator = new DoubleSumAggregator("name", "fieldName");

        DruidMovingAverageQuery.builder()
                .dataSource(new TableDataSource("dataSource"))
                .granularity(new SimpleGranularity(PredefinedGranularity.FIFTEEN_MINUTE))
                .intervals(singletonList(interval))
                .aggregations(singletonList(aggregator))
                .build();
    }

    @Test(dataProvider = "averagerTypesProvider")
    public void testAllAveragerTypes(String averagerName,
                                     MovingAverageCreators.Creator averagerCreator)
            throws Exception {
        String expectedJson = loadExpectedJsonForType(averagerName);

        DruidMovingAverageQuery query = simpleQuery(averagerCreator);

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @DataProvider(name = "averagerTypesProvider")
    private static Object[][] averagerTypesProvider() {
        return new Object[][]{
                {"doubleMax", (MovingAverageCreators.Creator) MovingAverageCreators::doubleMax},
                {"doubleMean", (MovingAverageCreators.Creator) MovingAverageCreators::doubleMean},
                {"doubleMeanNoNulls", (MovingAverageCreators.Creator) MovingAverageCreators::doubleMeanNoNulls},
                {"doubleMin", (MovingAverageCreators.Creator) MovingAverageCreators::doubleMin},
                {"doubleSum", (MovingAverageCreators.Creator) MovingAverageCreators::doubleSum},
                {"longMax", (MovingAverageCreators.Creator) MovingAverageCreators::longMax},
                {"longMean", (MovingAverageCreators.Creator) MovingAverageCreators::longMean},
                {"longMeanNoNulls", (MovingAverageCreators.Creator) MovingAverageCreators::longMeanNoNulls},
                {"longMin", (MovingAverageCreators.Creator) MovingAverageCreators::longMin},
                {"longSum", (MovingAverageCreators.Creator) MovingAverageCreators::longSum}
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

    private static DruidMovingAverageQuery simpleQuery(
            MovingAverageCreators.Creator averagerCreator) {
        ZonedDateTime startTime = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2020, 2, 29, 23, 59, 59, 0, ZoneOffset.UTC);
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

}
