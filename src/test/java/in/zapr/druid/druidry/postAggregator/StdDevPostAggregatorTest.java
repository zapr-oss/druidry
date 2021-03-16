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

public class StdDevPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testStdDevPostAggregatorAllFields() throws JsonProcessingException, JSONException {

        StdDevPostAggregator stdDevPostAggregator
                = new StdDevPostAggregator("Hello", "someField");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "stddev");
        jsonObject.put("name", "Hello");
        jsonObject.put("fieldName", "someField");

        String actualJSON = objectMapper.writeValueAsString(stdDevPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        StdDevPostAggregator stdDevPostAggregator =
            new StdDevPostAggregator(null, "someField");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullValue() {

        StdDevPostAggregator stdDevPostAggregator =
            new StdDevPostAggregator("Name", null);
    }

    @Test
    public void testEqualsPositive() {
        StdDevPostAggregator aggregator1
                = new StdDevPostAggregator("Hello", "someField");
        StdDevPostAggregator aggregator2
                = new StdDevPostAggregator("Hello", "someField");

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        StdDevPostAggregator aggregator1
                = new StdDevPostAggregator("Hello", "someField");
        StdDevPostAggregator aggregator2
                = new StdDevPostAggregator("Pi", "anotherField");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        StdDevPostAggregator aggregator1
                = new StdDevPostAggregator("Hello", "someField");
        FieldAccessPostAggregator aggregator2
                = new FieldAccessPostAggregator("Hello", "blah");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}
