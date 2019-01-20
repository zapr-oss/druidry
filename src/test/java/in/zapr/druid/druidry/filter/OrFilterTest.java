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

package in.zapr.druid.druidry.filter;

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

public class OrFilterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        SelectorFilter selectorFilter = new SelectorFilter("Hello", "World");
        SelectorFilter selectorFilter1 = new SelectorFilter("Peace", "Bro");

        OrFilter filter = new OrFilter(Arrays.asList(selectorFilter, selectorFilter1));

        JSONObject selector1 = new JSONObject();
        selector1.put("type", "selector");
        selector1.put("dimension", "Hello");
        selector1.put("value", "World");

        JSONObject selector2 = new JSONObject();
        selector2.put("type", "selector");
        selector2.put("dimension", "Peace");
        selector2.put("value", "Bro");

        JSONArray filterJsonArray = new JSONArray(Arrays.asList(selector1, selector2));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "or");
        jsonObject.put("fields", filterJsonArray);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldsMissing() {
        OrFilter andFilter = new OrFilter(null);
    }

}
