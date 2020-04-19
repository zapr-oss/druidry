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
public class HyperUniqueAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getHyperUniqueAggregatorJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "hyperUnique");
        jsonObject.put("name", "CarpeDiem");
        jsonObject.put("fieldName", "Hey");

        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        HyperUniqueAggregator hyperUniqueAggregator = HyperUniqueAggregator.builder()
                .name("CarpeDiem")
                .fieldName("Hey")
                .round(true)
                .build();

        JSONObject jsonObject = getHyperUniqueAggregatorJSON();
        jsonObject.put("round", true);

        String actualJSON = objectMapper.writeValueAsString(hyperUniqueAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        HyperUniqueAggregator hyperUniqueAggregator = HyperUniqueAggregator.builder()
                .name("CarpeDiem")
                .fieldName("Hey")
                .build();

        JSONObject jsonObject = getHyperUniqueAggregatorJSON();

        String actualJSON = objectMapper.writeValueAsString(hyperUniqueAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {

        HyperUniqueAggregator hyperUniqueAggregator = HyperUniqueAggregator.builder()
                .fieldName("Haha")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {

        HyperUniqueAggregator hyperUniqueAggregator = HyperUniqueAggregator.builder()
                .name("Name")
                .build();
    }

    @Test
    public void testEqualsPositive() {

        HyperUniqueAggregator aggregator1 = HyperUniqueAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();
        HyperUniqueAggregator aggregator2 = HyperUniqueAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {

        HyperUniqueAggregator aggregator1 = HyperUniqueAggregator.builder()
                .name("name1")
                .fieldName("field1")
                .build();
        HyperUniqueAggregator aggregator2 = HyperUniqueAggregator.builder()
                .name("name2")
                .fieldName("field2")
                .build();

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {

        HyperUniqueAggregator aggregator1 = HyperUniqueAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();
        CountAggregator aggregator2 = new CountAggregator("countAgg1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}