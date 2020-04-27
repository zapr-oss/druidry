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

package in.zapr.druid.druidry.topNMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.query.config.SortingOrder;

public class DimensionMetricTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getDimensionMetricJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimension");
        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        DimensionMetric dimensionMetric = DimensionMetric.builder()
                .ordering(SortingOrder.LEXICOGRAPHIC)
                .previousStop("x")
                .build();

        JSONObject jsonObject = getDimensionMetricJSON();
        jsonObject.put("ordering", "lexicographic");
        jsonObject.put("previousStop", "x");

        String actualJSON = objectMapper.writeValueAsString(dimensionMetric);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        DimensionMetric dimensionMetric = DimensionMetric.builder()
                .build();

        JSONObject jsonObject = getDimensionMetricJSON();

        String actualJSON = objectMapper.writeValueAsString(dimensionMetric);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testEqualsPositive() {
        DimensionMetric dimensionMetric1 = DimensionMetric.builder()
                .ordering(SortingOrder.STRLEN)
                .build();
        DimensionMetric dimensionMetric2 = DimensionMetric.builder()
                .ordering(SortingOrder.STRLEN)
                .build();

        Assert.assertEquals(dimensionMetric1, dimensionMetric2);
    }

    @Test
    public void testEqualsNegative() {
        DimensionMetric dimensionMetric1 = DimensionMetric.builder()
                .ordering(SortingOrder.STRLEN)
                .build();
        DimensionMetric dimensionMetric2 = DimensionMetric.builder()
                .ordering(SortingOrder.ALPHANUMERIC)
                .build();

        Assert.assertNotEquals(dimensionMetric1, dimensionMetric2);
    }
}