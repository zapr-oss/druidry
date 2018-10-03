package in.zapr.druid.druidry.query.scan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.SortingOrder;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.dimension.SimpleDimension;
import in.zapr.druid.druidry.filter.AndFilter;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.filter.searchQuerySpec.InsensitiveContainsSearchQuerySpec;
import in.zapr.druid.druidry.filter.searchQuerySpec.SearchQuerySpec;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.query.search.DruidSearchQuery;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DruidScanQueryTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testSampleQuery() throws JsonProcessingException, JSONException {


        List<DruidDimension> searchDimensions
                = Arrays.asList(new SimpleDimension("dim1"), new SimpleDimension("dim2"));

        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 3, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidFilter filter = new SelectorFilter("dim1","value1");

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource("sample_datasource")
                .columns(searchDimensions)
                .filter(filter)
                .resultFormat("list")
                .intervals(Collections.singletonList(interval))
                .batchSize(10000)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"scan\",\n" +
                "  \"dataSource\": \"sample_datasource\",\n" +

                "  \"columns\": [\n" +
                "    \"dim1\",\n" +
                "    \"dim2\"\n" +
                "  ],\n" +
                "  \"filter\": {\n" +
                "    \"type\": \"selector\",\n" +
                "    \"dimension\": \"dim1\",\n" +
                "    \"value\": \"value1\"\n" +
                "  },\n" +
                "  \"resultFormat\": \"list\",\n" +
                "  \"batchSize\": 10000,\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        SearchQuerySpec searchQuerySpec = new InsensitiveContainsSearchQuerySpec("Ke");

        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 3, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource("sample_datasource")
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"scan\",\n" +
                "  \"dataSource\": \"sample_datasource\",\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }

}
