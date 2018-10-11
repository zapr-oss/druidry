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

package in.zapr.druid.druidry.query.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.SortingOrder;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.dimension.SimpleDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.filter.searchQuerySpec.InsensitiveContainsSearchQuerySpec;
import in.zapr.druid.druidry.filter.searchQuerySpec.SearchQuerySpec;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;

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

        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 3, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidSearchQuery query = DruidSearchQuery.builder()
                .dataSource("sample_datasource")
                .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
                .searchDimensions(searchDimensions)
                .query(searchQuerySpec)
                .sort(SortingOrder.LEXICOGRAPHIC)
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"search\",\n" +
                "  \"dataSource\": \"sample_datasource\",\n" +
                "  \"granularity\": \"day\",\n" +
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

        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 3, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidSearchQuery query = DruidSearchQuery.builder()
                .dataSource("sample_datasource")
                .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
                .query(searchQuerySpec)
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"search\",\n" +
                "  \"dataSource\": \"sample_datasource\",\n" +
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

        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 3, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidFilter druidFilter = new SelectorFilter("Dim", "You");

        Context context = Context.builder()
                .useCache(true)
                .build();

        DruidSearchQuery query = DruidSearchQuery.builder()
                .dataSource("sample_datasource")
                .granularity(new SimpleGranularity(PredefinedGranularity.DAY))
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
                "  \"dataSource\": \"sample_datasource\",\n" +
                "  \"granularity\": \"day\",\n" +
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
