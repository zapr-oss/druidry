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

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HyperUniqueCardinalityPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testArithmeticPostAggregatorAllFields() throws JsonProcessingException, JSONException {

        HyperUniqueCardinalityPostAggregator hyperUniqueCardinalityPostAggregator
                = new HyperUniqueCardinalityPostAggregator("Hello", "World");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "hyperUniqueCardinality");
        jsonObject.put("name", "Hello");
        jsonObject.put("fieldName", "World");

        String actualJSON = objectMapper.writeValueAsString(hyperUniqueCardinalityPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        HyperUniqueCardinalityPostAggregator hyperUniqueCardinalityPostAggregator
                = new HyperUniqueCardinalityPostAggregator(null, "Haha");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() {

        HyperUniqueCardinalityPostAggregator hyperUniqueCardinalityPostAggregator
                = new HyperUniqueCardinalityPostAggregator("Name",
                null);
    }

    @Test
    public void testEqualsPositive() {
        HyperUniqueCardinalityPostAggregator aggregator1
                = new HyperUniqueCardinalityPostAggregator("Hello", "World");
        HyperUniqueCardinalityPostAggregator aggregator2
                = new HyperUniqueCardinalityPostAggregator("Hello", "World");

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        HyperUniqueCardinalityPostAggregator aggregator1
                = new HyperUniqueCardinalityPostAggregator("Hello", "World");
        HyperUniqueCardinalityPostAggregator aggregator2
                = new HyperUniqueCardinalityPostAggregator("Hola", "Duniya");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        HyperUniqueCardinalityPostAggregator aggregator1
                = new HyperUniqueCardinalityPostAggregator("Hello", "Yaha");
        FieldAccessPostAggregator aggregator2
                = new FieldAccessPostAggregator("Hello", "Yaha");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}