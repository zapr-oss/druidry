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

package in.zapr.druid.druidry.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongMinAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        LongMinAggregator longMinAggregator = new LongMinAggregator("CarpeDiem",
                "Hey");

        longMinAggregator.setExpression("(\"foo\" / \"bar\")");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "longMin");
        jsonObject.put("name", "CarpeDiem");
        jsonObject.put("fieldName", "Hey");
        jsonObject.put("expression", "(\"foo\" / \"bar\")");

        String actualJSON = objectMapper.writeValueAsString(longMinAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {

        LongMinAggregator longMinAggregator = new LongMinAggregator(null, "Haha");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {

        LongMinAggregator longMinAggregator = new LongMinAggregator("Name", null);
    }

    @Test
    public void testEqualsPositive() {
        LongMinAggregator aggregator1 = new LongMinAggregator("name", "field");
        LongMinAggregator aggregator2 = new LongMinAggregator("name", "field");

        LongMinAggregator aggregator3 = new LongMinAggregator("name", "field");

        aggregator3.setExpression("(\"foo\" / \"bar\")");

        LongMinAggregator aggregator4 = new LongMinAggregator("name", "field");

        aggregator4.setExpression("(\"foo\" / \"bar\")");

        Assert.assertEquals(aggregator1, aggregator2);
        Assert.assertEquals(aggregator3, aggregator4);
    }

    @Test
    public void testEqualsNegative() {
        LongMinAggregator aggregator1 = new LongMinAggregator("name", "field");
        LongMinAggregator aggregator2 = new LongMinAggregator("name1", "field1");

        LongMinAggregator aggregator3 = new LongMinAggregator("name", "field");

        aggregator3.setExpression("(\"foo\" / \"bar\")");

        LongMinAggregator aggregator4 = new LongMinAggregator("name", "field");

        aggregator4.setExpression("(\"foo\" / \"baz\")");

        Assert.assertNotEquals(aggregator1, aggregator2);
        Assert.assertNotEquals(aggregator3, aggregator4);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        LongMinAggregator aggregator1 = new LongMinAggregator("name", "field");
        CountAggregator aggregator2 = new CountAggregator("countAgg1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}
