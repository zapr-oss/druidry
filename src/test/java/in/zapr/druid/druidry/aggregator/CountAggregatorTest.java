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

import java.util.Collections;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        CountAggregator countAggregator = new CountAggregator("CarpeDiem");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "count");
        jsonObject.put("name", "CarpeDiem");

        String actualJSON = objectMapper.writeValueAsString(countAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {

        CountAggregator countAggregator = new CountAggregator(null);
    }

    @Test
    public void testEqualsPositive() {
        CountAggregator aggregator1 = new CountAggregator("countAgg");
        CountAggregator aggregator2 = new CountAggregator("countAgg");

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        CountAggregator aggregator1 = new CountAggregator("countAgg1");
        CountAggregator aggregator2 = new CountAggregator("countAgg2");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        CardinalityAggregator aggregator1 = CardinalityAggregator.builder()
                .name("Agg1")
                .fields(Collections.singletonList("Haha"))
                .byRow(true)
                .build();
        CountAggregator aggregator2 = new CountAggregator("countAgg1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}