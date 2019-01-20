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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaScriptAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        JavaScriptAggregator javaScriptAggregator = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(fields)
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();

        JSONArray fieldJsonArray = new JSONArray(fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "javascript");
        jsonObject.put("name", "Hello");
        jsonObject.put("fieldNames", fieldJsonArray);
        jsonObject.put("fnAggregate", "aggregate");
        jsonObject.put("fnCombine", "combine");
        jsonObject.put("fnReset", "reset");

        String actualJSON = objectMapper.writeValueAsString(javaScriptAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingNameField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        JavaScriptAggregator javaScriptAggregator = JavaScriptAggregator.builder()
                .fieldNames(fields)
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingFieldNamesField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        JavaScriptAggregator javaScriptAggregator = JavaScriptAggregator.builder()
                .name("Hello")
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingAggregateFunctionField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        JavaScriptAggregator javaScriptAggregator = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(fields)
                .functionCombine("combine")
                .functionReset("reset")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingCombineFunctionField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        JavaScriptAggregator javaScriptAggregator = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(fields)
                .functionAggregate("aggregate")
                .functionReset("reset")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingResetFunctionField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        JavaScriptAggregator javaScriptAggregator = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(fields)
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .build();
    }

    @Test
    public void testEqualsPositive() {
        JavaScriptAggregator aggregator1 = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(Collections.singletonList("ListElement1"))
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();

        JavaScriptAggregator aggregator2 = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(Collections.singletonList("ListElement1"))
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        JavaScriptAggregator aggregator1 = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(Collections.singletonList("ListElement1"))
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();

        JavaScriptAggregator aggregator2 = JavaScriptAggregator.builder()
                .name("Hello1")
                .fieldNames(Collections.singletonList("ListElement1"))
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        JavaScriptAggregator aggregator1 = JavaScriptAggregator.builder()
                .name("Hello")
                .fieldNames(Collections.singletonList("ListElement1"))
                .functionAggregate("aggregate")
                .functionCombine("combine")
                .functionReset("reset")
                .build();

        CountAggregator aggregator2 = new CountAggregator("countAgg1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

}