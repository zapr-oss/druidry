/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package query.aggregation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zapr.druidquery.Context;
import com.zapr.druidquery.Interval;
import com.zapr.druidquery.aggregator.CountAggregator;
import com.zapr.druidquery.aggregator.DoubleSumAggregator;
import com.zapr.druidquery.aggregator.DruidAggregator;
import com.zapr.druidquery.aggregator.LongSumAggregator;
import com.zapr.druidquery.dimension.DruidDimension;
import com.zapr.druidquery.dimension.SimpleDimension;
import com.zapr.druidquery.filter.AndFilter;
import com.zapr.druidquery.filter.DruidFilter;
import com.zapr.druidquery.filter.SelectorFilter;
import com.zapr.druidquery.granularity.Granularity;
import com.zapr.druidquery.granularity.PredefinedGranularity;
import com.zapr.druidquery.granularity.SimpleGranularity;
import com.zapr.druidquery.postAggregator.ArithmeticFunction;
import com.zapr.druidquery.postAggregator.ArithmeticPostAggregator;
import com.zapr.druidquery.postAggregator.ConstantPostAggregator;
import com.zapr.druidquery.postAggregator.DruidPostAggregator;
import com.zapr.druidquery.postAggregator.FieldAccessPostAggregator;
import com.zapr.druidquery.query.aggregation.DruidTopNQuery;
import com.zapr.druidquery.topNMetric.SimpleMetric;
import com.zapr.druidquery.topNMetric.TopNMetric;

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

public class TopNQueryTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSampleQuery() throws JsonProcessingException, JSONException {

        SelectorFilter selectorFilter1 = new SelectorFilter("dim1", "some_value");
        SelectorFilter selectorFilter2 = new SelectorFilter("dim2", "some_other_val");

        AndFilter filter = new AndFilter(Arrays.asList(selectorFilter1, selectorFilter2));

        DruidAggregator aggregator1 = new LongSumAggregator("count", "count");
        DruidAggregator aggregator2 = new DoubleSumAggregator("some_metric", "some_metric");

        FieldAccessPostAggregator fieldAccessPostAggregator1
                = new FieldAccessPostAggregator("some_metric", "some_metric");

        FieldAccessPostAggregator fieldAccessPostAggregator2
                = new FieldAccessPostAggregator("count", "count");

        DruidPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .name("sample_divide")
                .function(ArithmeticFunction.DIVIDE)
                .fields(Arrays.asList(fieldAccessPostAggregator1, fieldAccessPostAggregator2))
                .build();

        DateTime startTime = new DateTime(2013, 8, 31, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 9, 3, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);

        DruidDimension dimension = new SimpleDimension("sample_dim");
        TopNMetric metric = new SimpleMetric("count");

        DruidTopNQuery query = DruidTopNQuery.builder()
                .dataSource("sample_data")
                .dimension(dimension)
                .threshold(5)
                .topNMetric(metric)
                .granularity(granularity)
                .filter(filter)
                .aggregators(Arrays.asList(aggregator1, aggregator2))
                .postAggregators(Collections.singletonList(postAggregator))
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"topN\",\n" +
                "  \"dataSource\": \"sample_data\",\n" +
                "  \"dimension\": \"sample_dim\",\n" +
                "  \"threshold\": 5,\n" +
                "  \"metric\": \"count\",\n" +
                "  \"granularity\": \"all\",\n" +
                "  \"filter\": {\n" +
                "    \"type\": \"and\",\n" +
                "    \"fields\": [\n" +
                "      {\n" +
                "        \"type\": \"selector\",\n" +
                "        \"dimension\": \"dim1\",\n" +
                "        \"value\": \"some_value\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"selector\",\n" +
                "        \"dimension\": \"dim2\",\n" +
                "        \"value\": \"some_other_val\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"aggregations\": [\n" +
                "    {\n" +
                "      \"type\": \"longSum\",\n" +
                "      \"name\": \"count\",\n" +
                "      \"fieldName\": \"count\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"doubleSum\",\n" +
                "      \"name\": \"some_metric\",\n" +
                "      \"fieldName\": \"some_metric\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"postAggregations\": [\n" +
                "    {\n" +
                "      \"type\": \"arithmetic\",\n" +
                "      \"name\": \"sample_divide\",\n" +
                "      \"fn\": \"/\",\n" +
                "      \"fields\": [\n" +
                "        {\n" +
                "          \"type\": \"fieldAccess\",\n" +
                "          \"name\": \"some_metric\",\n" +
                "          \"fieldName\": \"some_metric\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"fieldAccess\",\n" +
                "          \"name\": \"count\",\n" +
                "          \"fieldName\": \"count\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"intervals\": [\n" +
                "    \"2013-08-31T00:00:00.000Z/2013-09-03T00:00:00.000Z\"\n" +
                "  ]\n" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        DateTime startTime = new DateTime(2013, 8, 31, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 9, 3, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidDimension dimension = new SimpleDimension("Demo");
        TopNMetric metric = new SimpleMetric("Let it work");

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.DAY);

        DruidTopNQuery query = DruidTopNQuery.builder()
                .dataSource("sample_data")
                .intervals(Collections.singletonList(interval))
                .granularity(granularity)
                .dimension(dimension)
                .threshold(7)
                .topNMetric(metric)
                .build();

        String actualJson = objectMapper.writeValueAsString(query);

        JSONObject expectedQuery = new JSONObject();
        expectedQuery.put("queryType", "topN");
        expectedQuery.put("dataSource", "sample_data");

        JSONArray array = new JSONArray(Collections.singletonList("2013-08-31T00:00:00.000Z/2013-09-03T00:00:00.000Z"));
        expectedQuery.put("intervals", array);
        expectedQuery.put("granularity", "day");
        expectedQuery.put("dimension", "Demo");
        expectedQuery.put("threshold", 7);
        expectedQuery.put("metric", "Let it work");

        JSONAssert.assertEquals(actualJson, expectedQuery, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {
        DateTime startTime = new DateTime(2013, 8, 31, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 9, 3, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidDimension dimension = new SimpleDimension("Demo");
        TopNMetric metric = new SimpleMetric("Let it work");

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.DAY);

        DruidFilter filter = new SelectorFilter("Spread", "Peace");
        DruidAggregator aggregator = new CountAggregator("Chill");
        DruidPostAggregator postAggregator = new ConstantPostAggregator("Keep", 16.11);
        Context context = Context.builder()
                .populateCache(true)
                .build();

        DruidTopNQuery query = DruidTopNQuery.builder()
                .dataSource("sample_data")
                .intervals(Collections.singletonList(interval))
                .granularity(granularity)
                .filter(filter)
                .aggregators(Collections.singletonList(aggregator))
                .postAggregators(Collections.singletonList(postAggregator))
                .dimension(dimension)
                .threshold(7)
                .topNMetric(metric)
                .context(context)
                .build();

        String actualJson = objectMapper.writeValueAsString(query);

        JSONObject expectedQuery = new JSONObject();
        expectedQuery.put("queryType", "topN");
        expectedQuery.put("dataSource", "sample_data");

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
        expectedPostAggregator.put("value", 16.11);

        JSONObject expectedContext = new JSONObject();
        expectedContext.put("populateCache", true);

        JSONArray array = new JSONArray(Collections.singletonList("2013-08-31T00:00:00.000Z/2013-09-03T00:00:00.000Z"));
        expectedQuery.put("intervals", array);
        expectedQuery.put("granularity", "day");
        expectedQuery.put("filter", expectedFilter);
        expectedQuery.put("aggregations", new JSONArray(Collections.singletonList(expectedAggregator)));
        expectedQuery.put("postAggregations", new JSONArray(Collections.singletonList(expectedPostAggregator)));
        expectedQuery.put("dimension", "Demo");
        expectedQuery.put("threshold", 7);
        expectedQuery.put("metric", "Let it work");
        expectedQuery.put("context", expectedContext);

        JSONAssert.assertEquals(actualJson, expectedQuery, JSONCompareMode.NON_EXTENSIBLE);
    }
}