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

public class ConstantPostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testConstantPostAggregatorAllFields() throws JsonProcessingException, JSONException {

        ConstantPostAggregator constantPostAggregator
                = new ConstantPostAggregator("Hello", 10.57);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "constant");
        jsonObject.put("name", "Hello");
        jsonObject.put("value", 10.57);

        String actualJSON = objectMapper.writeValueAsString(constantPostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        ConstantPostAggregator constantPostAggregator = new ConstantPostAggregator(null,
                10.57);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullValue() {

        ConstantPostAggregator constantPostAggregator = new ConstantPostAggregator("Name",
                null);
    }

    @Test
    public void testEqualsPositive() {
        ConstantPostAggregator aggregator1
                = new ConstantPostAggregator("Hello", 10.57);
        ConstantPostAggregator aggregator2
                = new ConstantPostAggregator("Hello", 10.57);

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        ConstantPostAggregator aggregator1
                = new ConstantPostAggregator("Hello", 10.57);
        ConstantPostAggregator aggregator2
                = new ConstantPostAggregator("Pi", 3.14);

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        ConstantPostAggregator aggregator1
                = new ConstantPostAggregator("Hello", 10.57);
        FieldAccessPostAggregator aggregator2
                = new FieldAccessPostAggregator("Hello", "Yaha");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}