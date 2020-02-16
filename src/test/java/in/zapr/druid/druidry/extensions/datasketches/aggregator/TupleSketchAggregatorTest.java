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

package in.zapr.druid.druidry.extensions.datasketches.aggregator;

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

public class TupleSketchAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getTupleSketchAggregatorJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "arrayOfDoublesSketch");
        jsonObject.put("name", "galaxy_tuple_sketch");
        jsonObject.put("fieldName", "galaxy");
        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        TupleSketchAggregator tupleSketchAggregator = TupleSketchAggregator.builder()
                .name("galaxy_tuple_sketch")
                .fieldName("galaxy")
                .nominalEntries(1024)
                .numberOfValues(1)
                .metricColumns(Arrays.asList("no_of_stars"))
                .build();

        JSONObject jsonObject = getTupleSketchAggregatorJSON();
        jsonObject.put("nominalEntries", 1024);
        jsonObject.put("numberOfValues", 1);
        jsonObject.put("metricColumns", new JSONArray(Arrays.asList("no_of_stars")));

        String actualJSON = objectMapper.writeValueAsString(tupleSketchAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {

        TupleSketchAggregator tupleSketchAggregator = TupleSketchAggregator.builder()
                .name("galaxy_tuple_sketch")
                .fieldName("galaxy")
                .build();

        JSONObject jsonObject = getTupleSketchAggregatorJSON();

        String actualJSON = objectMapper.writeValueAsString(tupleSketchAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void preconditionCheck() {

        TupleSketchAggregator tupleSketchAggregator = TupleSketchAggregator.builder()
                .name("galaxy_tuple_sketch")
                .fieldName("galaxy")
                .nominalEntries(3)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        TupleSketchAggregator tupleSketchAggregator = TupleSketchAggregator.builder()
                .fieldName("galaxy")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() {

        TupleSketchAggregator tupleSketchAggregator = TupleSketchAggregator.builder()
                .name("galaxy_tuple_sketch")
                .build();
    }
}