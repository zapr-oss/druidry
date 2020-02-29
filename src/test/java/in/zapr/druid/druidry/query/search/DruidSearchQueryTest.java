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

package in.zapr.druid.druidry.query.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.dataSource.TableDataSource;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.dimension.SimpleDimension;
import in.zapr.druid.druidry.dimension.enums.OutputType;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.filter.searchQuerySpec.InsensitiveContainsSearchQuerySpec;
import in.zapr.druid.druidry.filter.searchQuerySpec.SearchQuerySpec;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.query.config.Context;
import in.zapr.druid.druidry.query.config.Interval;
import in.zapr.druid.druidry.query.config.SortingOrder;
import in.zapr.druid.druidry.virtualColumn.ExpressionVirtualColumn;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DruidSearchQueryTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSampleQuery() throws JsonProcessingException, JSONException {

        List<DruidDimension> searchDimensions
                = Arrays.asList(new SimpleDimension("dim1"), new SimpleDimension("dim2"));

        SearchQuerySpec searchQuerySpec = new InsensitiveContainsSearchQuerySpec("Ke");

        Temporal startTime = ZonedDateTime.of(2013, 1, 1,
                                              0, 0, 0, 0, ZoneOffset.UTC);
        Temporal endTime = ZonedDateTime.of(2013, 1, 3,
                                            0, 0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidSearchQuery query = DruidSearchQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
                .virtualColumns(Collections.singletonList(new ExpressionVirtualColumn("dim3", "dim1 + dim2", OutputType.FLOAT)))
                .searchDimensions(searchDimensions)
                .query(searchQuerySpec)
                .sort(SortingOrder.LEXICOGRAPHIC)
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"search\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"granularity\": \"day\",\n" +
                "  \"virtualColumns\": [{\n" +
                "    \"type\": \"expression\",\n" +
                "    \"name\": \"dim3\",\n" +
                "    \"outputType\": \"FLOAT\",\n" +
                "    \"expression\": \"dim1 + dim2\"\n" +
                "  }],\n" +
                "  \"searchDimensions\": [\n" +
                "    \"dim1\",\n" +
                "    \"dim2\"\n" +
                "  ],\n" +
                "  \"query\": {\n" +
                "    \"type\": \"insensitive_contains\",\n" +
                "    \"value\": \"Ke\"\n" +
                "  },\n" +
                "  \"sort\" : {\n" +
                "    \"type\": \"lexicographic\"\n" +
                "  }," +
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

        Temporal startTime = ZonedDateTime.of(2013, 1, 1,
                                              0, 0, 0, 0, ZoneOffset.UTC);
        Temporal endTime = ZonedDateTime.of(2013, 1, 3,
                                            0, 0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidSearchQuery query = DruidSearchQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
                .query(searchQuerySpec)
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"search\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"granularity\": \"day\",\n" +
                "  \"query\": {\n" +
                "    \"type\": \"insensitive_contains\",\n" +
                "    \"value\": \"Ke\"\n" +
                "  },\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        List<DruidDimension> searchDimensions
                = Arrays.asList(new SimpleDimension("dim1"), new SimpleDimension("dim2"));

        SearchQuerySpec searchQuerySpec = new InsensitiveContainsSearchQuerySpec("Ke");

        Temporal startTime = ZonedDateTime.of(2013, 1, 1,
                                              0, 0, 0, 0, ZoneOffset.UTC);
        Temporal endTime = ZonedDateTime.of(2013, 1, 3,
                                            0, 0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidFilter druidFilter = new SelectorFilter("Dim", "You");

        Context context = Context.builder()
                .useCache(true)
                .build();

        DruidSearchQuery query = DruidSearchQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
                .virtualColumns(Collections.singletonList(new ExpressionVirtualColumn("dim3", "dim1 + dim2", OutputType.FLOAT)))
                .filter(druidFilter)
                .limit(16)
                .searchDimensions(searchDimensions)
                .query(searchQuerySpec)
                .sort(SortingOrder.LEXICOGRAPHIC)
                .intervals(Collections.singletonList(interval))
                .context(context)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"search\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"granularity\": \"day\",\n" +
                "  \"virtualColumns\": [{\n" +
                "    \"type\": \"expression\",\n" +
                "    \"name\": \"dim3\",\n" +
                "    \"outputType\": \"FLOAT\",\n" +
                "    \"expression\": \"dim1 + dim2\"\n" +
                "  }],\n" +
                "  \"filter\": {\n" +
                "        \"type\": \"selector\",\n" +
                "        \"dimension\": \"Dim\",\n" +
                "        \"value\": \"You\"\n" +
                "    },\n" +
                "    \"limit\": 16," +
                "  \"searchDimensions\": [\n" +
                "    \"dim1\",\n" +
                "    \"dim2\"\n" +
                "  ],\n" +
                "  \"query\": {\n" +
                "    \"type\": \"insensitive_contains\",\n" +
                "    \"value\": \"Ke\"\n" +
                "  },\n" +
                "  \"sort\" : {\n" +
                "    \"type\": \"lexicographic\"\n" +
                "  }," +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]," +
                "  \"context\": {\n" +
                "    \"useCache\" : true\n" +
                "    }" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }
}
