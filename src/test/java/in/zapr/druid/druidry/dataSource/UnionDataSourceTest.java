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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class UnionDataSourceTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testUnionDataSource() throws JsonProcessingException, JSONException {
        List<String> dataSourcesList = Arrays.asList("datasource_1", "datasource_2");

        UnionDataSource unionDataSource = new UnionDataSource(dataSourcesList);

        JSONObject dataSource = new JSONObject();
        dataSource.put("type", "union");

        JSONArray dataSources = new JSONArray(dataSourcesList);
        dataSource.put("dataSources", dataSources);

        String actualJson = objectMapper.writeValueAsString(unionDataSource);
        JSONAssert.assertEquals(actualJson, dataSource, JSONCompareMode.NON_EXTENSIBLE);
    }


    @Test(expectedExceptions = NullPointerException.class)
    public void testNullableDataSourceList() {
        new UnionDataSource(null);
    }
}

