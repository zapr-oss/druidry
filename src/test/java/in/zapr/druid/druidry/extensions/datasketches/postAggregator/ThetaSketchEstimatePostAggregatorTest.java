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

public class ThetaSketchEstimatePostAggregatorTest {

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
    public void testRequiredFieldsWithFieldAccess() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", fieldAccessPostAggregator);


        JSONObject fieldAccess = getFieldAccessJSON();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchEstimate");
        jsonObject.put("name", "estimate_stars");
        jsonObject.put("field", fieldAccess);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchEstimatePostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testAllFieldsWithFieldAccess() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", fieldAccessPostAggregator, 2);


        JSONObject fieldAccess = getFieldAccessJSON();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchEstimate");
        jsonObject.put("name", "estimate_stars");
        jsonObject.put("errorBoundsStdDev", 2);
        jsonObject.put("field", fieldAccess);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchEstimatePostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testAllFieldsWithThetaSketchSetOp() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .name("name")
                .function(ThetaSketchFunction.INTERSECT)
                .fields(Collections.singletonList(fieldAccessPostAggregator))
                .build();

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", thetaSketchSetOpPostAggregator);


        JSONObject fieldAccess = getFieldAccessJSON();

        JSONArray fields = new JSONArray(Collections.singletonList(getFieldAccessJSON()));

        JSONObject thetaSketchSetOp = new JSONObject();
        thetaSketchSetOp.put("type", "thetaSketchSetOp");
        thetaSketchSetOp.put("name", "name");
        thetaSketchSetOp.put("func", "INTERSECT");
        thetaSketchSetOp.put("fields", fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchEstimate");
        jsonObject.put("name", "estimate_stars");
        jsonObject.put("field", thetaSketchSetOp);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchEstimatePostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator(null, new FieldAccessPostAggregator("stars"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullField() {

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", null);
    }

}
