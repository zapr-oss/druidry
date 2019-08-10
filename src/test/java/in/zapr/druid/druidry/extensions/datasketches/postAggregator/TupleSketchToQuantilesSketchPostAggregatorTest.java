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

public class TupleSketchToQuantilesSketchPostAggregatorTest {

    private static ObjectMapper objectMapper;

    private FieldAccessPostAggregator milkyWay;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
        milkyWay = new FieldAccessPostAggregator("MilkyWay");
    }

    private JSONObject getTupleSketchToQuantilesSketchPostAggregatorJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "arrayOfDoublesSketchToQuantilesSketch");
        jsonObject.put("name", "milky_way_quantile_sketch");
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

        TupleSketchToQuantilesSketchPostAggregator tupleSketchToQuantilesSketchPostAggregator =
                TupleSketchToQuantilesSketchPostAggregator.builder()
                        .name("milky_way_quantile_sketch")
                        .field(milkyWay)
                        .column(1)
                        .k(1024)
                        .build();

        JSONObject jsonObject = getTupleSketchToQuantilesSketchPostAggregatorJSON();
        jsonObject.put("field", (getFieldAccessPostAggregatorJSON("MilkyWay")));
        jsonObject.put("column", 1);
        jsonObject.put("k", 1024);

        String actualJSON = objectMapper.writeValueAsString(tupleSketchToQuantilesSketchPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        TupleSketchToQuantilesSketchPostAggregator tupleSketchToQuantilesSketchPostAggregator =
                TupleSketchToQuantilesSketchPostAggregator.builder()
                        .name("milky_way_quantile_sketch")
                        .field(milkyWay)
                        .build();

        JSONObject jsonObject = getTupleSketchToQuantilesSketchPostAggregatorJSON();
        jsonObject.put("field", getFieldAccessPostAggregatorJSON("MilkyWay"));

        String actualJSON = objectMapper.writeValueAsString(tupleSketchToQuantilesSketchPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        TupleSketchToQuantilesSketchPostAggregator tupleSketchToQuantilesSketchPostAggregator =
                TupleSketchToQuantilesSketchPostAggregator.builder()
                        .field(milkyWay)
                        .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullField() {

        TupleSketchToQuantilesSketchPostAggregator tupleSketchToQuantilesSketchPostAggregator =
                TupleSketchToQuantilesSketchPostAggregator.builder()
                        .name("milky_way_quantile_sketch")
                        .build();
    }
}