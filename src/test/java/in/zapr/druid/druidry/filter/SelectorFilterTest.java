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

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class SelectorFilterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testStringField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", "World");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", "World");

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testIntegerField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", 5);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", 5);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testLongField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", 1488498926000L);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", 1488498926000L);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithStringField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", Optional.ofNullable("World"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", "World");

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithIntegerField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", Optional.ofNullable(5));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", 5);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithLongField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", Optional.ofNullable(1488498926000L));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", 1488498926000L);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithNullField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", Optional.ofNullable(null));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.putOpt("value", JSONObject.NULL);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissing() {
        String dimension = null;
        SelectorFilter filter = new SelectorFilter(dimension, "World");
    }
}
