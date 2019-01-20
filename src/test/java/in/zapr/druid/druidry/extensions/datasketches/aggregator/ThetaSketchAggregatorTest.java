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

package in.zapr.druid.druidry.extensions.datasketches.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ThetaSketchAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getThetaSketchAggregatorJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketch");
        jsonObject.put("name", "estimated_stars");
        jsonObject.put("fieldName", "stars");

        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        ThetaSketchAggregator thetaSketchAggregator = ThetaSketchAggregator.builder()
                .name("estimated_stars")
                .fieldName("stars")
                .isInputThetaSketch(true)
                .size(1024L)
                .build();

        JSONObject jsonObject = getThetaSketchAggregatorJSON();
        jsonObject.put("isInputThetaSketch", true);
        jsonObject.put("size", 1024L);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        ThetaSketchAggregator thetaSketchAggregator = ThetaSketchAggregator.builder()
                .name("estimated_stars")
                .fieldName("stars")
                .build();

        JSONObject jsonObject = getThetaSketchAggregatorJSON();

        String actualJSON = objectMapper.writeValueAsString(thetaSketchAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void preconditionCheck() {

        ThetaSketchAggregator thetaSketchAggregator = ThetaSketchAggregator.builder()
                .name("estimated_stars")
                .fieldName("stars")
                .size(420L)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        ThetaSketchAggregator thetaSketchAggregator = ThetaSketchAggregator.builder()
                .fieldName("stars")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() {

        ThetaSketchAggregator thetaSketchAggregator = ThetaSketchAggregator.builder()
                .name("estimated_stars")
                .build();
    }

}
