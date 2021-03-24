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

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class RoundPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRoundPostAggregatorAllFields() throws JsonProcessingException, JSONException {

        RoundPostAggregator roundPostAggregator
                = new RoundPostAggregator("Hello", "someField", 2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "expression");
        jsonObject.put("name", "Hello");
        jsonObject.put("fieldName", "someField");
        jsonObject.put("expression", "round(someField, 2)");

        String actualJSON = objectMapper.writeValueAsString(roundPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        RoundPostAggregator roundPostAggregator =
            new RoundPostAggregator(null, "someField", 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullValue() {

        RoundPostAggregator roundPostAggregator =
            new RoundPostAggregator("Name", null, 1);
    }

    @Test
    public void testEqualsPositive() {
        RoundPostAggregator aggregator1
                = new RoundPostAggregator("Hello", "someField", 2);
        RoundPostAggregator aggregator2
                = new RoundPostAggregator("Hello", "someField", 2);

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        RoundPostAggregator aggregator1
                = new RoundPostAggregator("Hello", "someField", 2);
        RoundPostAggregator aggregator2
                = new RoundPostAggregator("Pi", "anotherField", 3);

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        RoundPostAggregator aggregator1
                = new RoundPostAggregator("Hello", "someField", 1);
        FieldAccessPostAggregator aggregator2
                = new FieldAccessPostAggregator("Hello", "blah");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}
