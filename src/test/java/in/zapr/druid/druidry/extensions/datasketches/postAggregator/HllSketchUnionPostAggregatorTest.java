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

import java.util.Arrays;

import in.zapr.druid.druidry.extensions.datasketches.aggregator.TargetHllType;
import in.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator;

public class HllSketchUnionPostAggregatorTest {

    private static ObjectMapper objectMapper;

    private FieldAccessPostAggregator milkyWayStarsHll;
    private FieldAccessPostAggregator andromedaStarsHll;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
        milkyWayStarsHll = new FieldAccessPostAggregator("milky_way_stars_hll");
        andromedaStarsHll = new FieldAccessPostAggregator("andromeda_stars_hll");
    }

    private JSONObject getHllSketchUnionPostAggregatorJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "HLLSketchUnion");
        jsonObject.put("name", "andro_way_hll");
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

        HllSketchUnionPostAggregator hllSketchUnionPostAggregator =
                HllSketchUnionPostAggregator.builder()
                        .name("andro_way_hll")
                        .fields(Arrays.asList(milkyWayStarsHll, andromedaStarsHll))
                        .lgK(4)
                        .targetHllType(TargetHllType.HLL_4)
                        .build();

        JSONObject jsonObject = getHllSketchUnionPostAggregatorJSON();
        jsonObject.put("fields", new JSONArray(
                Arrays.asList(
                        getFieldAccessPostAggregatorJSON("milky_way_stars_hll"),
                        getFieldAccessPostAggregatorJSON("andromeda_stars_hll")
                ))
        );
        jsonObject.put("lgK", 4);
        jsonObject.put("tgtHllType", "HLL_4");

        String actualJSON = objectMapper.writeValueAsString(hllSketchUnionPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        HllSketchUnionPostAggregator hllSketchUnionPostAggregator =
                HllSketchUnionPostAggregator.builder()
                        .name("andro_way_hll")
                        .fields(Arrays.asList(milkyWayStarsHll, andromedaStarsHll))
                        .build();

        JSONObject jsonObject = getHllSketchUnionPostAggregatorJSON();
        jsonObject.put("fields", new JSONArray(
                Arrays.asList(
                        getFieldAccessPostAggregatorJSON("milky_way_stars_hll"),
                        getFieldAccessPostAggregatorJSON("andromeda_stars_hll")
                ))
        );

        String actualJSON = objectMapper.writeValueAsString(hllSketchUnionPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        HllSketchUnionPostAggregator hllSketchUnionPostAggregator =
                HllSketchUnionPostAggregator.builder()
                        .fields(Arrays.asList(milkyWayStarsHll, andromedaStarsHll))
                        .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullField() {

        HllSketchUnionPostAggregator hllSketchUnionPostAggregator =
                HllSketchUnionPostAggregator.builder()
                        .name("andro_way_hll")
                        .build();
    }
}