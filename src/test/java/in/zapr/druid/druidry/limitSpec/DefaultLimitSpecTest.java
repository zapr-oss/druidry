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

package in.zapr.druid.druidry.limitSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import in.zapr.druid.druidry.query.config.SortingOrder;
import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpecMap;
import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpecString;

public class DefaultLimitSpecTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testDefaultLimitSpecWithColumnName() throws JSONException, JsonProcessingException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "default");
        jsonObject.put("limit", 7);
        jsonObject.put("offset", 0);
        JSONArray columns = new JSONArray(Collections.singleton("Tagore"));
        jsonObject.put("columns", columns);

        DefaultLimitSpec defaultLimitSpec = new DefaultLimitSpec(7, Collections.singletonList(new OrderByColumnSpecString("Tagore")));

        String actualJson = objectMapper.writeValueAsString(defaultLimitSpec);
        JSONAssert.assertEquals(actualJson, jsonObject, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testDefaultLimitSpecWithOffset() throws JSONException, JsonProcessingException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "default");
        jsonObject.put("limit", 7);
        jsonObject.put("offset", 10);
        JSONArray columns = new JSONArray(Collections.singleton("Tagore"));
        jsonObject.put("columns", columns);

        DefaultLimitSpec defaultLimitSpec = new DefaultLimitSpec(7, 10, Collections.singletonList(new OrderByColumnSpecString("Tagore")));

        String actualJson = objectMapper.writeValueAsString(defaultLimitSpec);
        JSONAssert.assertEquals(actualJson, jsonObject, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testDefaultLimitSpecWithColumnSpecMap() throws JSONException, JsonProcessingException {

        String dimension = "dim";
        OrderByColumnSpecMap orderByColumnSpecMap
                = new OrderByColumnSpecMap(dimension, true, SortingOrder.ALPHANUMERIC);

        JSONObject orderByColumnSpecJson = new JSONObject();
        orderByColumnSpecJson.put("dimension", dimension);
        orderByColumnSpecJson.put("direction", "ascending");
        orderByColumnSpecJson.put("dimensionOrder", "alphanumeric");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "default");
        jsonObject.put("limit", 13);
        jsonObject.put("offset", 0);

        JSONArray orderByColumnSpecArray
                = new JSONArray(Collections.singletonList(orderByColumnSpecJson));
        jsonObject.put("columns", orderByColumnSpecArray);

        DefaultLimitSpec defaultLimitSpec = new DefaultLimitSpec(13, 0,
                Collections.singletonList(orderByColumnSpecMap));

        String actualJson = objectMapper.writeValueAsString(defaultLimitSpec);
        JSONAssert.assertEquals(actualJson, jsonObject, JSONCompareMode.NON_EXTENSIBLE);
    }
}