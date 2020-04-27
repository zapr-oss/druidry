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

package in.zapr.druid.druidry.query.scan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import in.zapr.druid.druidry.dataSource.TableDataSource;
import in.zapr.druid.druidry.dimension.enums.OutputType;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import in.zapr.druid.druidry.query.config.Interval;
import in.zapr.druid.druidry.virtualColumn.ExpressionVirtualColumn;

public class DruidScanQueryTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testSampleQuery() throws JsonProcessingException, JSONException {


        List<String> searchDimensions
                = Arrays.asList("dim1", "dim2");

        ZonedDateTime startTime = ZonedDateTime.of(2013, 1, 1,
                0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2013, 1, 3,
                0, 0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidFilter filter = new SelectorFilter("dim1", "value1");

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .columns(searchDimensions)
                .virtualColumns(Collections.singletonList(new ExpressionVirtualColumn("dim3", "dim1 + dim2", OutputType.FLOAT)))
                .filter(filter)
                .resultFormat(ResultFormat.LIST)
                .intervals(Collections.singletonList(interval))
                .batchSize(10000)
                .limit(1000L)
                .legacy(true)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"scan\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"columns\": [\n" +
                "    \"dim1\",\n" +
                "    \"dim2\"\n" +
                "  ],\n" +
                "  \"virtualColumns\": [{\n" +
                "    \"type\": \"expression\",\n" +
                "    \"name\": \"dim3\",\n" +
                "    \"outputType\": \"FLOAT\",\n" +
                "    \"expression\": \"dim1 + dim2\"\n" +
                "  }],\n" +
                "  \"filter\": {\n" +
                "    \"type\": \"selector\",\n" +
                "    \"dimension\": \"dim1\",\n" +
                "    \"value\": \"value1\"\n" +
                "  },\n" +
                "  \"resultFormat\": \"list\",\n" +
                "  \"batchSize\": 10000,\n" +
                "  \"limit\": 1000,\n" +
                "  \"legacy\": true,\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(expectedJsonAsString, actualJson, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        ZonedDateTime startTime = ZonedDateTime.of(2013, 1, 1,
                0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2013, 1, 3,
                0, 0, 0, 0, ZoneOffset.UTC);

        Interval interval = new Interval(startTime, endTime);

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .intervals(Collections.singletonList(interval))
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"scan\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(expectedJsonAsString, actualJson, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void preconditionLimitCheck() {

        ZonedDateTime startTime = ZonedDateTime.of(2013, 1, 1, 0,
                0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2013, 1, 3, 0,
                0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .intervals(Collections.singletonList(interval))
                .limit(-1L)
                .build();

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void preconditionBatchSizeCheck() {

        ZonedDateTime startTime = ZonedDateTime.of(2013, 1, 1,
                0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2013, 1, 3,
                0, 0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .intervals(Collections.singletonList(interval))
                .batchSize(-1)
                .build();

    }

    @Test
    public void testSampleQueryWithEmptyLines() throws JsonProcessingException, JSONException {


        List<String> searchDimensions = Collections.emptyList();

        ZonedDateTime startTime = ZonedDateTime.of(2013, 1, 1,
                0, 0, 0, 0, ZoneOffset.UTC);
        ZonedDateTime endTime = ZonedDateTime.of(2013, 1, 3,
                0, 0, 0, 0, ZoneOffset.UTC);
        Interval interval = new Interval(startTime, endTime);

        DruidFilter filter = new SelectorFilter("dim1", "value1");

        DruidScanQuery query = DruidScanQuery.builder()
                .dataSource(new TableDataSource("sample_datasource"))
                .columns(searchDimensions)
                .filter(filter)
                .resultFormat(ResultFormat.LIST)
                .intervals(Collections.singletonList(interval))
                .batchSize(10000)
                .limit(1000L)
                .legacy(true)
                .build();

        String expectedJsonAsString = "{\n" +
                "  \"queryType\": \"scan\",\n" +
                "  \"dataSource\": {\n" +
                "    \"type\": \"table\",\n" +
                "    \"name\": \"sample_datasource\"\n" +
                "  },\n" +
                "  \"columns\": [\n" +
                "],\n" +
                "  \"filter\": {\n" +
                "    \"type\": \"selector\",\n" +
                "    \"dimension\": \"dim1\",\n" +
                "    \"value\": \"value1\"\n" +
                "  },\n" +
                "  \"resultFormat\": \"list\",\n" +
                "  \"batchSize\": 10000,\n" +
                "  \"limit\": 1000,\n" +
                "  \"legacy\": true,\n" +
                "  \"intervals\": [" +
                "    \"2013-01-01T00:00:00.000Z/2013-01-03T00:00:00.000Z\"" +
                "  ]" +
                "}";

        String actualJson = objectMapper.writeValueAsString(query);
        JSONAssert.assertEquals(expectedJsonAsString, actualJson, JSONCompareMode.NON_EXTENSIBLE);

    }
}
