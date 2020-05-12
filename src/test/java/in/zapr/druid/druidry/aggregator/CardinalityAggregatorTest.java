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

public class CardinalityAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .name("Hello")
                .fields(fields)
                .byRow(true)
                .round(true)
                .build();

        JSONArray fieldJsonArray = new JSONArray(fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "cardinality");
        jsonObject.put("name", "Hello");
        jsonObject.put("fields", fieldJsonArray);
        jsonObject.put("byRow", true);
        jsonObject.put("round", true);

        String actualJSON = objectMapper.writeValueAsString(cardinalityAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .name("Hello")
                .fields(fields)
                .build();

        JSONArray fieldJsonArray = new JSONArray(fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "cardinality");
        jsonObject.put("name", "Hello");
        jsonObject.put("fields", fieldJsonArray);

        String actualJSON = objectMapper.writeValueAsString(cardinalityAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingNameField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .fields(fields)
                .byRow(true)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingFieldsField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .name("Haha")
                .byRow(true)
                .build();
    }

    @Test
    public void testEqualsPositive() {
        CardinalityAggregator aggregator1 = CardinalityAggregator.builder()
                .name("Agg1")
                .fields(Collections.singletonList("Haha"))
                .byRow(true)
                .build();
        CardinalityAggregator aggregator2 = CardinalityAggregator.builder()
                .name("Agg1")
                .fields(Collections.singletonList("Haha"))
                .byRow(true)
                .build();

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        CardinalityAggregator aggregator1 = CardinalityAggregator.builder()
                .name("Agg1")
                .fields(Collections.singletonList("Haha"))
                .byRow(true)
                .build();
        CardinalityAggregator aggregator2 = CardinalityAggregator.builder()
                .name("Agg1")
                .fields(Collections.singletonList("Haha1"))
                .byRow(false)
                .build();

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        CardinalityAggregator aggregator1 = CardinalityAggregator.builder()
                .name("Agg1")
                .fields(Collections.singletonList("Haha"))
                .byRow(true)
                .round(true)
                .build();
        CountAggregator aggregator2 = new CountAggregator("count");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}