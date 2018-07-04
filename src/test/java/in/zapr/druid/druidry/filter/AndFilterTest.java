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

package in.zapr.druid.druidry.filter;

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

public class AndFilterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        SelectorFilter selectorFilter = new SelectorFilter("Hello", "World");
        SelectorFilter selectorFilter1 = new SelectorFilter("Peace", "Bro");

        AndFilter filter = new AndFilter(Arrays.asList(selectorFilter, selectorFilter1));

        JSONObject selector1 = new JSONObject();
        selector1.put("type", "selector");
        selector1.put("dimension", "Hello");
        selector1.put("value", "World");

        JSONObject selector2 = new JSONObject();
        selector2.put("type", "selector");
        selector2.put("dimension", "Peace");
        selector2.put("value", "Bro");

        JSONArray filterJsonArray = new JSONArray(Arrays.asList(selector1, selector2));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "and");
        jsonObject.put("fields", filterJsonArray);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldsMissing() {
        AndFilter andFilter = new AndFilter(null);
    }

    @Test
    public void testEquals() {
        DruidFilter selectorFilter = new SelectorFilter("Hello", "World");
        DruidFilter selectorFilter1 = new SelectorFilter("Hello", "World");

        Assert.assertEquals(selectorFilter, selectorFilter1);
    }

}
