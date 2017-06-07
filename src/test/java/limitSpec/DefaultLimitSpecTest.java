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

package limitSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.SortingOrder;
import in.zapr.druid.druidry.limitSpec.DefaultLimitSpec;
import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpecMap;
import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpecString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

public class DefaultLimitSpecTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testDefaultLimitSpecWithColumnName() throws JSONException, JsonProcessingException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "default");
        jsonObject.put("limit", 7);
        JSONArray columns = new JSONArray(Collections.singleton("Tagore"));
        jsonObject.put("columns", columns);

        DefaultLimitSpec defaultLimitSpec = new DefaultLimitSpec(7,
                Collections.singletonList(new OrderByColumnSpecString("Tagore")));

        String actualJson = objectMapper.writeValueAsString(defaultLimitSpec);
        JSONAssert.assertEquals(actualJson, jsonObject, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testDefaultLimitSpecWithColumnSpecMap() throws JSONException, JsonProcessingException {

        String dimension = "dim";
        OrderByColumnSpecMap orderByColumnSpecMap
                = new OrderByColumnSpecMap(dimension, true, SortingOrder.ALPHANUMERIC);

        JSONObject orderByColumnSpecJson = new JSONObject();
        orderByColumnSpecJson.put("dimension", dimension);
        orderByColumnSpecJson.put("direction", "ascending");
        orderByColumnSpecJson.put("dimensionOrder", "alphanumeric");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "default");
        jsonObject.put("limit", 13);

        JSONArray orderByColumnSpecArray
                = new JSONArray(Collections.singletonList(orderByColumnSpecJson));
        jsonObject.put("columns", orderByColumnSpecArray);

        DefaultLimitSpec defaultLimitSpec = new DefaultLimitSpec(13,
                Collections.singletonList(orderByColumnSpecMap));

        String actualJson = objectMapper.writeValueAsString(defaultLimitSpec);
        JSONAssert.assertEquals(actualJson, jsonObject, JSONCompareMode.NON_EXTENSIBLE);
    }
}