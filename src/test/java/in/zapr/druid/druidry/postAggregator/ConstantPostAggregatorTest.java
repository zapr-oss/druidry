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

package in.zapr.druid.druidry.postAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConstantPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testConstantPostAggregatorAllFields() throws JsonProcessingException, JSONException {

        ConstantPostAggregator constantPostAggregator
                = new ConstantPostAggregator("Hello", 10.57);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "constant");
        jsonObject.put("name", "Hello");
        jsonObject.put("value", 10.57);

        String actualJSON = objectMapper.writeValueAsString(constantPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        ConstantPostAggregator constantPostAggregator = new ConstantPostAggregator(null,
                10.57);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullValue() {

        ConstantPostAggregator constantPostAggregator = new ConstantPostAggregator("Name",
                null);
    }

    @Test
    public void testEqualsPositive() {
        ConstantPostAggregator aggregator1
                = new ConstantPostAggregator("Hello", 10.57);
        ConstantPostAggregator aggregator2
                = new ConstantPostAggregator("Hello", 10.57);

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        ConstantPostAggregator aggregator1
                = new ConstantPostAggregator("Hello", 10.57);
        ConstantPostAggregator aggregator2
                = new ConstantPostAggregator("Pi", 3.14);

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        ConstantPostAggregator aggregator1
                = new ConstantPostAggregator("Hello", 10.57);
        FieldAccessPostAggregator aggregator2
                = new FieldAccessPostAggregator("Hello", "Yaha");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}