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

public class HllSketchMergeAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getHllSketchMergeAggregatorJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "HLLSketchMerge");
        jsonObject.put("name", "stars_hll");
        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        HllSketchMergeAggregator hllSketchMergeAggregator = HllSketchMergeAggregator.builder()
                .name("stars_hll")
                .fieldName("stars")
                .lgK(4)
                .targetHllType(TargetHllType.HLL_4)
                .build();

        JSONObject jsonObject = getHllSketchMergeAggregatorJSON();
        jsonObject.put("fieldName", "stars");
        jsonObject.put("lgK", 4);
        jsonObject.put("tgtHllType", "HLL_4");

        String actualJSON = objectMapper.writeValueAsString(hllSketchMergeAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        HllSketchMergeAggregator hllSketchMergeAggregator = HllSketchMergeAggregator.builder()
                .name("stars_hll")
                .fieldName("stars")
                .build();

        JSONObject jsonObject = getHllSketchMergeAggregatorJSON();
        jsonObject.put("fieldName", "stars");

        String actualJSON = objectMapper.writeValueAsString(hllSketchMergeAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        HllSketchMergeAggregator hllSketchMergeAggregator = HllSketchMergeAggregator.builder()
                .fieldName("stars")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() {

        HllSketchMergeAggregator hllSketchMergeAggregator = HllSketchMergeAggregator.builder()
                .name("stars_hll")
                .build();
    }
}