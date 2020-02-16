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

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.query.config.SortingOrder;
import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpecMap;

public class OrderByColumnSpecMapTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testOrderColumnSpecOnlyDimensionField() throws JSONException, JsonProcessingException {
        String dimension = "dim";
        OrderByColumnSpecMap orderByColumnSpecMap
                = new OrderByColumnSpecMap(dimension, true, SortingOrder.ALPHANUMERIC);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dimension", dimension);
        jsonObject.put("direction", "ascending");
        jsonObject.put("dimensionOrder", "alphanumeric");

        String actualJson = objectMapper.writeValueAsString(orderByColumnSpecMap);

        JSONAssert.assertEquals(actualJson, jsonObject, JSONCompareMode.NON_EXTENSIBLE);
    }
}
