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
public class LongSumAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        LongSumAggregator longSumAggregator = new LongSumAggregator("CarpeDiem",
                "Hey");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "longSum");
        jsonObject.put("name", "CarpeDiem");
        jsonObject.put("fieldName", "Hey");

        String actualJSON = objectMapper.writeValueAsString(longSumAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFieldsButFieldName() throws JsonProcessingException, JSONException {

        LongSumAggregator longSumAggregator =
            LongSumAggregator.builder()
                .name("CarpeDiem")
                .expression("(\"foo\" / \"bar\")")
                .build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "longSum");
        jsonObject.put("name", "CarpeDiem");
        jsonObject.put("expression", "(\"foo\" / \"bar\")");

        String actualJSON = objectMapper.writeValueAsString(longSumAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {

        LongSumAggregator longSumAggregator =
            LongSumAggregator.builder()
                .fieldName("Haha")
                .build();
    }

    @Test
    public void testEqualsPositive() {
        LongSumAggregator aggregator1 =
            LongSumAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();

        LongSumAggregator aggregator2 =
            LongSumAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();

        LongSumAggregator aggregator3 =
            LongSumAggregator.builder()
                .name("name")
                .expression("(\"foo\" / \"bar\")")
                .build();

        LongSumAggregator aggregator4 =
            LongSumAggregator.builder()
                .name("name")
                .expression("(\"foo\" / \"bar\")")
                .build();

        Assert.assertEquals(aggregator1, aggregator2);
        Assert.assertEquals(aggregator3, aggregator4);
    }

    @Test
    public void testEqualsNegative() {
        LongSumAggregator aggregator1 =
            LongSumAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();

        LongSumAggregator aggregator2 =
            LongSumAggregator.builder()
                .name("name1")
                .fieldName("field1")
                .build();

        LongSumAggregator aggregator3 =
            LongSumAggregator.builder()
                .name("name")
                .expression("(\"foo\" / \"bar\")")
                .build();

        LongSumAggregator aggregator4 =
            LongSumAggregator.builder()
                .name("name")
                .expression("(\"foo\" / \"baz\")")
                .build();

        Assert.assertNotEquals(aggregator1, aggregator2);
        Assert.assertNotEquals(aggregator3, aggregator4);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        LongSumAggregator aggregator1 =
            LongSumAggregator.builder()
                .name("name")
                .fieldName("field")
                .build();

        CountAggregator aggregator2 = new CountAggregator("countAgg1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

}
