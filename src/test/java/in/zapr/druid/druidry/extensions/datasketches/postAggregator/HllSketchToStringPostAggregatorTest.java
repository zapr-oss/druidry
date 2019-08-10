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

package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator;

public class HllSketchToStringPostAggregatorTest {

    private static ObjectMapper objectMapper;

    private FieldAccessPostAggregator starsHll;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
        starsHll = new FieldAccessPostAggregator("stars_hll");
    }

    private JSONObject getHllSketchToStringPostAggregatorJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "HLLSketchToString");
        jsonObject.put("name", "stars_estimate");
        return jsonObject;
    }

    private JSONObject getFieldAccessPostAggregatorJSON(String fieldName) throws JSONException {
        JSONObject fieldAccess = new JSONObject();
        fieldAccess.put("type", "fieldAccess");
        fieldAccess.put("fieldName", fieldName);

        return fieldAccess;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        HllSketchToStringPostAggregator hllSketchToStringPostAggregator =
                HllSketchToStringPostAggregator.builder()
                        .name("stars_estimate")
                        .field(starsHll)
                        .build();

        JSONObject jsonObject = getHllSketchToStringPostAggregatorJSON();
        jsonObject.put("field", getFieldAccessPostAggregatorJSON("stars_hll"));

        String actualJSON = objectMapper.writeValueAsString(hllSketchToStringPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        HllSketchToStringPostAggregator hllSketchToStringPostAggregator =
                HllSketchToStringPostAggregator.builder()
                        .field(starsHll)
                        .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullField() {

        HllSketchToStringPostAggregator hllSketchToStringPostAggregator =
                HllSketchToStringPostAggregator.builder()
                        .name("stars_estimate")
                        .build();
    }
}