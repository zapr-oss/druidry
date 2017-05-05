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

package aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zapr.druidquery.aggregator.CardinalityAggregator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CardinalityAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .name("Hello")
                .fields(fields)
                .byRow(true)
                .build();

        JSONArray fieldJsonArray = new JSONArray(fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "cardinality");
        jsonObject.put("name", "Hello");
        jsonObject.put("fields", fieldJsonArray);
        jsonObject.put("byRow", true);

        String actualJSON = objectMapper.writeValueAsString(cardinalityAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .name("Hello")
                .fields(fields)
                .build();

        JSONArray fieldJsonArray = new JSONArray(fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "cardinality");
        jsonObject.put("name", "Hello");
        jsonObject.put("fields", fieldJsonArray);

        String actualJSON = objectMapper.writeValueAsString(cardinalityAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingNameField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .fields(fields)
                .byRow(true)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingFieldsField() throws JsonProcessingException, JSONException {

        List<String> fields = Arrays.asList("Cardinal", "Aggregator");

        CardinalityAggregator cardinalityAggregator = CardinalityAggregator.builder()
                .name("Haha")
                .byRow(true)
                .build();
    }
}