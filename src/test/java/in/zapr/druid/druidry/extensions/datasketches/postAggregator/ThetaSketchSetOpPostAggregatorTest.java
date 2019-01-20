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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import in.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator;

public class ThetaSketchSetOpPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getFieldAccessJSON() throws JSONException {
        JSONObject fieldAccess = new JSONObject();
        fieldAccess.put("type", "fieldAccess");
        fieldAccess.put("fieldName", "stars");

        return fieldAccess;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .name("name")
                .function(ThetaSketchFunction.INTERSECT)
                .fields(Collections.singletonList(fieldAccessPostAggregator))
                .size(1024L)
                .build();

        JSONObject fieldAccess = getFieldAccessJSON();

        JSONArray fields = new JSONArray(Collections.singletonList(getFieldAccessJSON()));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchSetOp");
        jsonObject.put("name", "name");
        jsonObject.put("func", "INTERSECT");
        jsonObject.put("fields", fields);
        jsonObject.put("size", 1024L);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchSetOpPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .name("name")
                .function(ThetaSketchFunction.INTERSECT)
                .fields(Collections.singletonList(fieldAccessPostAggregator))
                .build();

        JSONObject fieldAccess = getFieldAccessJSON();

        JSONArray fields = new JSONArray(Collections.singletonList(getFieldAccessJSON()));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchSetOp");
        jsonObject.put("name", "name");
        jsonObject.put("func", "INTERSECT");
        jsonObject.put("fields", fields);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchSetOpPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .function(ThetaSketchFunction.INTERSECT)
                .fields(Collections.singletonList(new FieldAccessPostAggregator("stars")))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFunction() {

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .name("estimate_stars")
                .fields(Collections.singletonList(new FieldAccessPostAggregator("stars")))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFields() {

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .name("estimate_stars")
                .function(ThetaSketchFunction.INTERSECT)
                .build();
    }

}
