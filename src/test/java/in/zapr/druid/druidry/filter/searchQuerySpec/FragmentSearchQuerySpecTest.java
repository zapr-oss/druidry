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

package in.zapr.druid.druidry.filter.searchQuerySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class FragmentSearchQuerySpecTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        List<String> values = Arrays.asList("value1", "value2");

        FragmentSearchQuerySpec spec = new FragmentSearchQuerySpec(values, true);

        JSONArray array = new JSONArray(values);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "fragment");
        jsonObject.put("values", array);
        jsonObject.put("caseSensitive", true);

        String actualJSON = objectMapper.writeValueAsString(spec);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JSONException, JsonProcessingException {
        List<String> values = Arrays.asList("value1", "value2");

        FragmentSearchQuerySpec spec = new FragmentSearchQuerySpec(values);

        JSONArray array = new JSONArray(values);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "fragment");
        jsonObject.put("values", array);

        String actualJSON = objectMapper.writeValueAsString(spec);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingValueField() throws JSONException, JsonProcessingException {
        FragmentSearchQuerySpec spec = new FragmentSearchQuerySpec(null);
    }
}
