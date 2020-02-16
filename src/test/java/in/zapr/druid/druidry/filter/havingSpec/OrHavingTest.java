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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class OrHavingTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        HavingSpec equalToHaving1 = new EqualToHaving("Hello", 14);
        HavingSpec equalToHaving2 = new EqualToHaving("Peace", 15);

        HavingSpec having = new OrHaving(Arrays.asList(equalToHaving1, equalToHaving2));

        JSONObject having1 = new JSONObject();
        having1.put("type", "equalTo");
        having1.put("aggregation", "Hello");
        having1.put("value", 14);

        JSONObject having2 = new JSONObject();
        having2.put("type", "equalTo");
        having2.put("aggregation", "Peace");
        having2.put("value", 15);

        JSONArray filterJsonArray = new JSONArray(Arrays.asList(having1, having2));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "or");
        jsonObject.put("havingSpecs", filterJsonArray);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();

        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldsMissing() {
        OrHaving andHaving = new OrHaving(null);
    }
}
