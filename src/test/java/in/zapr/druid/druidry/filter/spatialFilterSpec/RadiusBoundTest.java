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

package in.zapr.druid.druidry.filter.spatialFilterSpec;

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

public class RadiusBoundTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        List<Double> coordValues = Arrays.asList(1.1, 2.2, 3.3);
        double radius = 5.5;
        SpatialFilterBoundSpec bound = new RadiusBound(coordValues, radius);

        JSONObject boundObject = new JSONObject();
        boundObject.put("type", "radius");
        boundObject.put("coords", new JSONArray(coordValues));
        boundObject.put("radius", radius);


        String actualJSON = objectMapper.writeValueAsString(bound);
        String expectedJSON = boundObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testCoordsMissing() {
        double radius = 5.5;
        SpatialFilterBoundSpec bound = new RadiusBound(null, radius);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testValueMissing() {
        List<Double> coordValues = Arrays.asList(1.1, 2.2, 3.3);
        SpatialFilterBoundSpec bound = new RadiusBound(coordValues, null);
    }

}
