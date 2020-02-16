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

import java.util.Optional;

public class DimSelectionHavingTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFieldsNumeric() throws JSONException, JsonProcessingException {
        HavingSpec having = new DimSelectorHaving("dimension_name", 2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", 2);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFieldsString() throws JSONException, JsonProcessingException {
        HavingSpec having = new DimSelectorHaving("dimension_name", "2");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", "2");

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithNumericField() throws JSONException, JsonProcessingException {
        HavingSpec having = new DimSelectorHaving("dimension_name", Optional.ofNullable(2));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", 2);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithStringField() throws JSONException, JsonProcessingException {
        HavingSpec having = new DimSelectorHaving("dimension_name", Optional.ofNullable("2"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", "2");

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testOptionalWithNullField() throws JSONException, JsonProcessingException {
        HavingSpec having = new DimSelectorHaving("dimension_name", Optional.ofNullable(null));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", JSONObject.NULL);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSettingRequiredFieldAsNull() {
        DimSelectorHaving having = new DimSelectorHaving(null, "World");
    }
}