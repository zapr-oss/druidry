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

package filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.filter.SelectorFilter;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SelectorFilterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testStringField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", "World");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", "World");

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testIntegerField() throws JSONException, JsonProcessingException {
        SelectorFilter filter = new SelectorFilter("Hello", 5);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "selector");
        jsonObject.put("dimension", "Hello");
        jsonObject.put("value", 5);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissing() {
        String dimension = null;
        SelectorFilter filter = new SelectorFilter(dimension, "World");
    }
}
