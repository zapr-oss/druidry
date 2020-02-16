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

package in.zapr.druid.druidry.filter.havingSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.filter.SelectorFilter;

public class FilterHavingTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLongField() throws JSONException, JsonProcessingException {
        HavingSpec filter = new FilterHaving(new SelectorFilter("Hello", 1488498926000L));
        JSONObject filterJsonObject = new JSONObject();
        filterJsonObject.put("type", "selector");
        filterJsonObject.put("dimension", "Hello");
        filterJsonObject.put("value", 1488498926000L);

        JSONObject havingJsonObject = new JSONObject();
        havingJsonObject.put("type", "filter");
        havingJsonObject.put("filter", filterJsonObject);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = havingJsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissing() {
        String dimension = null;
        HavingSpec filter = new FilterHaving(null);
    }
}

