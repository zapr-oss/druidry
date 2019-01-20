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

package in.zapr.druid.druidry.dimension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.dimension.enums.OutputType;

public class DefaultDimensionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        DefaultDimension defaultDimension = new DefaultDimension("name",
                "nombre", OutputType.STRING);

        String actualJSON = objectMapper.writeValueAsString(defaultDimension);

        String expectedJSONString = "{\n  \"type\": \"default\",\n  \"dimension\": " +
                "\"name\",\n  \"outputName\": \"nombre\",\n  \"outputType\": \"STRING\"\n}";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {
        DefaultDimension defaultDimension = new DefaultDimension("name",
                "nombre", null);

        String actualJSON = objectMapper.writeValueAsString(defaultDimension);


        String expectedJSONString = "{\n  \"type\": \"default\",\n  \"dimension\": " +
                "\"name\",\n  \"outputName\": \"nombre\"\n}";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissingFields() {
        DefaultDimension defaultDimension = new DefaultDimension(null, "nombre",
                OutputType.STRING);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOutputNameMissingFields() throws JsonProcessingException, JSONException {
        DefaultDimension defaultDimension = new DefaultDimension("name",
                null, OutputType.STRING);
    }

    @Test
    public void testEqualsPositive() {
        DefaultDimension dimension1 = new DefaultDimension("name",
                "nombre", OutputType.STRING);
        DefaultDimension dimension2 = new DefaultDimension("name",
                "nombre", OutputType.STRING);

        Assert.assertEquals(dimension1, dimension2);
    }

    @Test
    public void testEqualsNegative() {
        DefaultDimension dimension1 = new DefaultDimension("name",
                "nombre", OutputType.STRING);
        DefaultDimension dimension2 = new DefaultDimension("name",
                "nombre", OutputType.LONG);

        Assert.assertNotEquals(dimension1, dimension2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        SimpleDimension dimension1 = new SimpleDimension("name");
        DefaultDimension dimension2 = new DefaultDimension("name",
                "output",
                OutputType.LONG);

        Assert.assertNotEquals(dimension1, dimension2);
    }
}
