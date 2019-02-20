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

package in.zapr.druid.druidry.query.select;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.zapr.druid.druidry.dataSource.TableDataSource;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;

import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;

public class DruidSelectQueryTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testSampleQuery() throws JsonProcessingException, JSONException {
        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 2, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        PagingSpec pagingSpec = new PagingSpec(5, new HashMap<>());

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);

        DruidSelectQuery query = DruidSelectQuery.builder()
                .dataSource(new TableDataSource("wikipedia"))
                .descending(false)
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .pagingSpec(pagingSpec)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"select\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"wikipedia\"\n" +
                "  },\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-02T00:00:00.000Z\"" +
                "  ]," +
                "  \"descending\": false,\n" +
                "  \"granularity\": \"all\",\n" +
                "  \"pagingSpec\": {\n" +
                "    \"threshold\": 5,\n" +
                "    \"pagingIdentifiers\": {}\n" +
                "  }\n" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testPagingQuery() throws JsonProcessingException, JSONException {
        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 2, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        HashMap<String, Integer> pagingIdentifiers = new HashMap<>();
        pagingIdentifiers.put("pagingIdentifier", 5);
        PagingSpec pagingSpec = new PagingSpec(5, false, pagingIdentifiers);

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);

        DruidSelectQuery query = DruidSelectQuery.builder()
                .dataSource(new TableDataSource("wikipedia"))
                .descending(false)
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .pagingSpec(pagingSpec)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"select\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"wikipedia\"\n" +
                "  },\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-02T00:00:00.000Z\"" +
                "  ]," +
                "  \"descending\": false,\n" +
                "  \"granularity\": \"all\",\n" +
                "  \"pagingSpec\": {\n" +
                "    \"threshold\": 5,\n" +
                "    \"fromNext\": false,\n" +
                "    \"pagingIdentifiers\": {" +
                "      \"pagingIdentifier\": 5" +
                "    }\n" +
                "  }\n" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(actualJson, expectedJsonAsString, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullableDataSource() {
        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 2, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        PagingSpec pagingSpec = new PagingSpec(5, new HashMap<>());

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);

        DruidSelectQuery.builder()
                .descending(false)
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .pagingSpec(pagingSpec)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullableInterval() {
        PagingSpec pagingSpec = new PagingSpec(5, new HashMap<>());

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);

        DruidSelectQuery.builder()
                .dataSource(new TableDataSource("dataSource"))
                .descending(false)
                .granularity(granularity)
                .pagingSpec(pagingSpec)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullablePagingSpec() {
        DateTime startTime = new DateTime(2013, 1, 1, 0,
                0, 0, DateTimeZone.UTC);
        DateTime endTime = new DateTime(2013, 1, 2, 0,
                0, 0, DateTimeZone.UTC);
        Interval interval = new Interval(startTime, endTime);

        Granularity granularity = new SimpleGranularity(PredefinedGranularity.ALL);

        DruidSelectQuery.builder()
                .dataSource(new TableDataSource("dataSource"))
                .descending(false)
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .build();
    }
}

