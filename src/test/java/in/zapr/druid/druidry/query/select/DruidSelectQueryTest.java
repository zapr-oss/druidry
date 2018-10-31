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

package in.zapr.druid.druidry.query.select;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.query.scan.DruidScanQuery;
import in.zapr.druid.druidry.query.scan.ResultFormat;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
                .dataSource("wikipedia")
                .descending(false)
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .pagingSpec(pagingSpec)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"select\",\n" +
                "  \"dataSource\": \"wikipedia\",\n" +
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
                .dataSource("wikipedia")
                .descending(false)
                .granularity(granularity)
                .intervals(Collections.singletonList(interval))
                .pagingSpec(pagingSpec)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"select\",\n" +
                "  \"dataSource\": \"wikipedia\",\n" +
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
}

