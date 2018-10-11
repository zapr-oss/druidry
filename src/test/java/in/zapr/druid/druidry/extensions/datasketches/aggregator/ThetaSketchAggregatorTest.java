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
