/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package in.zapr.druid.druidry.postAggregator;

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

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArithmeticPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private List<JSONObject> getDruidAggregatorJSONs() throws JSONException {
        JSONObject fieldJsonObject = new JSONObject();
        fieldJsonObject.put("type", "fieldAccess");
        fieldJsonObject.put("name", "Hello");
        fieldJsonObject.put("fieldName", "World");

        return Collections.singletonList(fieldJsonObject);
    }

    @Test
    public void testArithmeticPostAggregatorAllFields() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators
                = Collections.singletonList(fieldAccessPostAggregator);

        ArithmeticPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators)
                .ordering(ArithmeticOrdering.NUMERIC_FIRST)
                .build();

        JSONArray fieldJsonArray = new JSONArray(getDruidAggregatorJSONs());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "arithmetic");
        jsonObject.put("name", "name");
        jsonObject.put("fn", "quotient");
        jsonObject.put("fields", fieldJsonArray);
        jsonObject.put("ordering", "numericFirst");

        String actualJSON = objectMapper.writeValueAsString(postAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testArithmeticPostAggregatorRequiredFields() throws JsonProcessingException,
            JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators
                = Collections.singletonList(fieldAccessPostAggregator);

        ArithmeticPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators)
                .build();

        JSONArray fieldJsonArray = new JSONArray(getDruidAggregatorJSONs());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "arithmetic");
        jsonObject.put("name", "name");
        jsonObject.put("fn", "quotient");
        jsonObject.put("fields", fieldJsonArray);

        String actualJSON = objectMapper.writeValueAsString(postAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testArithmeticPostAggregatorMissingNameFields() {

        FieldAccessPostAggregator fieldAccessPostAggregator
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators
                = Collections.singletonList(fieldAccessPostAggregator);

        ArithmeticPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testArithmeticPostAggregatorMissingFunctionFields() {

        FieldAccessPostAggregator fieldAccessPostAggregator
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators
                = Collections.singletonList(fieldAccessPostAggregator);

        ArithmeticPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .name("name")
                .fields(druidPostAggregators)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testArithmeticPostAggregatorMissingFields() {

        ArithmeticPostAggregator postAggregator = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .build();
    }

    @Test
    public void testEqualsPositive() {
        FieldAccessPostAggregator fieldAccessPostAggregator1
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators1
                = Collections.singletonList(fieldAccessPostAggregator1);

        ArithmeticPostAggregator aggregator1 = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators1)
                .ordering(ArithmeticOrdering.NUMERIC_FIRST)
                .build();

        FieldAccessPostAggregator fieldAccessPostAggregator2
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators2
                = Collections.singletonList(fieldAccessPostAggregator2);

        ArithmeticPostAggregator aggregator2 = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators2)
                .ordering(ArithmeticOrdering.NUMERIC_FIRST)
                .build();

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        FieldAccessPostAggregator fieldAccessPostAggregator1
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators1
                = Collections.singletonList(fieldAccessPostAggregator1);

        ArithmeticPostAggregator aggregator1 = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators1)
                .ordering(ArithmeticOrdering.NUMERIC_FIRST)
                .build();

        FieldAccessPostAggregator fieldAccessPostAggregator2
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators2
                = Collections.singletonList(fieldAccessPostAggregator2);

        ArithmeticPostAggregator aggregator2 = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.DIVIDE)
                .fields(druidPostAggregators2)
                .ordering(ArithmeticOrdering.NUMERIC_FIRST)
                .build();

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        FieldAccessPostAggregator fieldAccessPostAggregator1
                = new FieldAccessPostAggregator("Hello", "World");

        List<DruidPostAggregator> druidPostAggregators1
                = Collections.singletonList(fieldAccessPostAggregator1);

        ArithmeticPostAggregator aggregator1 = ArithmeticPostAggregator.builder()
                .name("name")
                .function(ArithmeticFunction.QUOTIENT)
                .fields(druidPostAggregators1)
                .ordering(ArithmeticOrdering.NUMERIC_FIRST)
                .build();

        FieldAccessPostAggregator aggregator2
                = new FieldAccessPostAggregator("Hello", "Yaha");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}