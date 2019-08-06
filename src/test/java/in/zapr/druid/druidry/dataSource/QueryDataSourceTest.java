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

package in.zapr.druid.druidry.dataSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.dimension.SimpleDimension;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.query.aggregation.DruidGroupByQuery;

public class QueryDataSourceTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testQueryDataSource() throws JsonProcessingException, JSONException {
        DruidDimension druidDimension1 = new SimpleDimension("dim1");
        DruidDimension druidDimension2 = new SimpleDimension("dim2");

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);
        // Interval
        DateTime startTime = new DateTime(2012, 1, 1, 0, 0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2012, 1, 3, 0, 0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidGroupByQuery druidGroupByQuery = DruidGroupByQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .dimensions(Arrays.asList(druidDimension1, druidDimension2))
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .build();

        QueryDataSource queryDataSource = new QueryDataSource(druidGroupByQuery);

        JSONObject dataSource = new JSONObject();
        dataSource.put("type", "table");
        dataSource.put("name", "sample_datasource");

        JSONObject expectedQuery = new JSONObject();
        expectedQuery.put("queryType", "groupBy");
        expectedQuery.put("dataSource", dataSource);

        JSONArray intervalArray = new JSONArray(Collections.singletonList("2012-01-01T00:00:00.000Z/2012-01-03T00:00:00.000Z"));
        expectedQuery.put("intervals", intervalArray);
        expectedQuery.put("granularity", "all");
        JSONArray dimensionArray = new JSONArray(Arrays.asList("dim1", "dim2"));
        expectedQuery.put("dimensions", dimensionArray);

        JSONObject queryDataSourceJson = new JSONObject();
        queryDataSourceJson.put("type", "query");
        queryDataSourceJson.put("query", expectedQuery);

        String actualJson = objectMapper.writeValueAsString(queryDataSource);
        JSONAssert.assertEquals(actualJson, queryDataSourceJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullableDataSourceQuery() {
        new QueryDataSource(null);
    }
}

