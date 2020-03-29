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

public class NumericMetricTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getNumericMetricJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "numeric");
        jsonObject.put("metric", "events");
        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        NumericMetric numericMetric = new NumericMetric(new SimpleMetric("events"));

        JSONObject jsonObject = getNumericMetricJSON();

        String actualJSON = objectMapper.writeValueAsString(numericMetric);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullField1() {

        NumericMetric numericMetric = new NumericMetric(null);
    }

    @Test
    public void testEqualsPositive() {

        NumericMetric numericMetric1 =  new NumericMetric(new SimpleMetric("events"));
        NumericMetric numericMetric2 =  new NumericMetric(new SimpleMetric("events"));

        Assert.assertEquals(numericMetric1, numericMetric2);
    }

    @Test
    public void testEqualsNegative() {

        NumericMetric numericMetric1 =  new NumericMetric(new SimpleMetric("events1"));
        NumericMetric numericMetric2 =  new NumericMetric(new SimpleMetric("events2"));

        Assert.assertNotEquals(numericMetric1, numericMetric2);
    }
}