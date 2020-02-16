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

public class QuantilesSketchAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getQuantilesSketchAggregatorJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "quantilesDoublesSketch");
        jsonObject.put("name", "star_age_quantiles_sketch");
        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        QuantilesSketchAggregator quantilesSketchAggregator = QuantilesSketchAggregator.builder()
                .name("star_age_quantiles_sketch")
                .fieldName("star_age")
                .k(1024)
                .build();

        JSONObject jsonObject = getQuantilesSketchAggregatorJSON();
        jsonObject.put("fieldName", "star_age");
        jsonObject.put("k", 1024);

        String actualJSON = objectMapper.writeValueAsString(quantilesSketchAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        QuantilesSketchAggregator quantilesSketchAggregator = QuantilesSketchAggregator.builder()
                .name("star_age_quantiles_sketch")
                .fieldName("star_age")
                .build();

        JSONObject jsonObject = getQuantilesSketchAggregatorJSON();
        jsonObject.put("fieldName", "star_age");

        String actualJSON = objectMapper.writeValueAsString(quantilesSketchAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void preconditionCheck() {

        QuantilesSketchAggregator quantilesSketchAggregator = QuantilesSketchAggregator.builder()
                .name("star_age_quantiles_sketch")
                .fieldName("star_age")
                .k(3)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        QuantilesSketchAggregator quantilesSketchAggregator = QuantilesSketchAggregator.builder()
                .fieldName("star_age")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() {

        QuantilesSketchAggregator quantilesSketchAggregator = QuantilesSketchAggregator.builder()
                .name("star_age_quantiles_sketch")
                .build();
    }
}