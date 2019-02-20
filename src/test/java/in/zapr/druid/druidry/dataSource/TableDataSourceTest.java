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
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.granularity.PredefinedGranularity;
import in.zapr.druid.druidry.granularity.SimpleGranularity;
import in.zapr.druid.druidry.query.select.DruidSelectQuery;
import in.zapr.druid.druidry.query.select.PagingSpec;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;

public class TableDataSourceTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testTableDataSource() throws JsonProcessingException, JSONException {
        TableDataSource tableDataSource = new TableDataSource("sample_datasource");

        JSONObject dataSource = new JSONObject();
        dataSource.put("type", "table");
        dataSource.put("name", "sample_datasource");

        String actualJson = objectMapper.writeValueAsString(tableDataSource);
        JSONAssert.assertEquals(actualJson, dataSource, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test(expectedExceptions = NullPointerException.class)
    public void testNullableDataSourceName() {
        new TableDataSource(null);
    }
}

