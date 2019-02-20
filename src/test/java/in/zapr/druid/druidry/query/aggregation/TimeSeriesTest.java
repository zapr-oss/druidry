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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import in.zapr.druid.druidry.dataSource.TableDataSource;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.aggregator.CountAggregator;
import in.zapr.druid.druidry.aggregator.DoubleSumAggregator;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.aggregator.LongSumAggregator;
import in.zapr.druid.druidry.filter.AndFilter;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.OrFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.postAggregator.ArithmeticFunction;
import in.zapr.druid.druidry.postAggregator.ArithmeticPostAggregator;
import in.zapr.druid.druidry.postAggregator.ConstantPostAggregator;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import in.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator;

public class TimeSeriesTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(com.fasterxml.jackson.databind.SerializationFeature.
                WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void testSampleQuery() throws JsonProcessingException, JSONException {

        SelectorFilter selectorFilter2 = new SelectorFilter("sample_dimension2",
                "sample_value2");
        SelectorFilter selectorFilter3 = new SelectorFilter("sample_dimension3",
                "sample_value3");

        OrFilter orfilter = new OrFilter(Arrays.asList(selectorFilter2, selectorFilter3));

        SelectorFilter selectorFilter1 = new SelectorFilter("sample_dimension1",
                "sample_value1");

        AndFilter andFilter = new AndFilter(Arrays.asList(selectorFilter1, orfilter));

        DruidAggregator aggregator1 = new LongSumAggregator("sample_name1",
                "sample_fieldName1");
        DruidAggregator aggregator2 = new DoubleSumAggregator("sample_name2",
                "sample_fieldName2");

        FieldAccessPostAggregator fieldAccessPostAggregator1
                = new FieldAccessPostAggregator("postAgg__sample_name1",
                "sample_name1");

        FieldAccessPostAggregator fieldAccessPostAggregator2
                = new FieldAccessPostAggregator("postAgg__sample_name2",
                "sample_name2");

        DruidPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .name("sample_divide")
                .function(ArithmeticFunction.DIVIDE)
                .fields(Arrays.asList(fieldAccessPostAggregator1, fieldAccessPostAggregator2))
                .build();

        //2013-08-31T00:00:00.000/2013-09-03T00:00:00.000"
        DateTime startTime = new DateTime(2012, 1, 1, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2012, 1, 3, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.DAY);

        DruidTimeSeriesQuery query = DruidTimeSeriesQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .granularity(granularity)
                .descending(true)
                .filter(andFilter)
                .aggregators(Arrays.asList(aggregator1, aggregator2))
                .postAggregators(Collections.singletonList(postAggregator))
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"timeseries\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"granularity\": \"day\",\n" +
                "  \"descending\": true,\n" +
                "  \"filter\": {\n" +
                "    \"type\": \"and\",\n" +
                "    \"fields\": [\n" +
                "      { \"type\": \"selector\", \"dimension\": \"sample_dimension1\", \"value\": \"sample_value1\" },\n" +
                "      { \"type\": \"or\",\n" +
                "        \"fields\": [\n" +
                "          { \"type\": \"selector\", \"dimension\": \"sample_dimension2\", \"value\": \"sample_value2\" },\n" +
                "          { \"type\": \"selector\", \"dimension\": \"sample_dimension3\", \"value\": \"sample_value3\" }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"aggregations\": [\n" +
                "    { \"type\": \"longSum\", \"name\": \"sample_name1\", \"fieldName\": \"sample_fieldName1\" },\n" +
                "    { \"type\": \"doubleSum\", \"name\": \"sample_name2\", \"fieldName\": \"sample_fieldName2\" }\n" +
                "  ],\n" +
                "  \"postAggregations\": [\n" +
                "    { \"type\": \"arithmetic\",\n" +
                "      \"name\": \"sample_divide\",\n" +
                "      \"fn\": \"/\",\n" +
                "      \"fields\": [\n" +
                "        { \"type\": \"fieldAccess\", \"name\": \"postAgg__sample_name1\", \"fieldName\": \"sample_name1\" },\n" +
                "        { \"type\": \"fieldAccess\", \"name\": \"postAgg__sample_name2\", \"fieldName\": \"sample_name2\" }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"intervals\": [ \"2012-01-01T00:00:00.000Z/2012-01-03T00:00:00.000Z\" ]\n" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {
        DateTime startTime = new DateTime(2013, 7, 14, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 11, 16, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.DAY);

        DruidTimeSeriesQuery seriesQuery = DruidTimeSeriesQuery.builder()
                .dataSource(new TableDataSource("Matrix"))
                .intervals(Collections.singletonList(interval))
                .granularity(granularity)
                .build();

        JSONObject dataSource = new JSONObject();
        dataSource.put("type", "table");
        dataSource.put("name", "Matrix");

        JSONObject expectedQuery = new JSONObject();
        expectedQuery.put("queryType", "timeseries");
        expectedQuery.put("dataSource", dataSource);
        expectedQuery.put("intervals", new JSONArray(Collections
                .singletonList("2013-07-14T00:00:00.000Z/2013-11-16T00:00:00.000Z")));
        expectedQuery.put("granularity", "day");

        String actualJson = objectMapper.writeValueAsString(seriesQuery);
        JSONAssert.assertEquals(actualJson, expectedQuery, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {
        DateTime startTime = new DateTime(2013, 7, 14, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 11, 16, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.DAY);

        Context context = Context.builder()
                .useCache(true)
                .build();

        DruidFilter filter = new SelectorFilter("Spread", "Peace");
        DruidAggregator aggregator = new CountAggregator("Chill");
        DruidPostAggregator postAggregator = new ConstantPostAggregator("Keep", 10.47);

        DruidTimeSeriesQuery seriesQuery = DruidTimeSeriesQuery.builder()
                .dataSource(new TableDataSource("Matrix"))
                .descending(true)
                .intervals(Collections.singletonList(interval))
                .granularity(granularity)
                .filter(filter)
                .aggregators(Collections.singletonList(aggregator))
                .postAggregators(Collections.singletonList(postAggregator))
                .context(context)
                .build();

        JSONObject expectedFilter = new JSONObject();
        expectedFilter.put("type", "selector");
        expectedFilter.put("dimension", "Spread");
        expectedFilter.put("value", "Peace");

        JSONObject expectedAggregator = new JSONObject();
        expectedAggregator.put("type", "count");
        expectedAggregator.put("name", "Chill");

        JSONObject expectedPostAggregator = new JSONObject();
        expectedPostAggregator.put("type", "constant");
        expectedPostAggregator.put("name", "Keep");
        expectedPostAggregator.put("value", 10.47);

        JSONObject expectedContext = new JSONObject();
        expectedContext.put("useCache", true);

        JSONObject dataSource = new JSONObject();
        dataSource.put("type", "table");
        dataSource.put("name", "Matrix");

        JSONObject expectedQuery = new JSONObject();
        expectedQuery.put("queryType", "timeseries");
        expectedQuery.put("dataSource", dataSource);
        expectedQuery.put("intervals", new JSONArray(Collections
                .singletonList("2013-07-14T00:00:00.000Z/2013-11-16T00:00:00.000Z")));
        expectedQuery.put("granularity", "day");
        expectedQuery.put("aggregations", new JSONArray(Collections.singletonList(expectedAggregator)));
        expectedQuery.put("postAggregations", new JSONArray(Collections.singletonList(expectedPostAggregator)));
        expectedQuery.put("filter", expectedFilter);
        expectedQuery.put("descending", true);
        expectedQuery.put("context", expectedContext);

        String actualJson = objectMapper.writeValueAsString(seriesQuery);
        JSONAssert.assertEquals(actualJson, expectedQuery, JSONCompareMode.NON_EXTENSIBLE);
    }
}