/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
