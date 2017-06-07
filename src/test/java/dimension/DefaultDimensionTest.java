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

package dimension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.dimension.DefaultDimension;
import in.zapr.druid.druidry.dimension.enums.OutputType;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DefaultDimensionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        DefaultDimension defaultDimension = new DefaultDimension("name", "nombre", OutputType.STRING);

        String actualJSON = objectMapper.writeValueAsString(defaultDimension);

        String expectedJSONString = "{\n  \"type\": \"default\",\n  \"dimension\": \"name\",\n  \"outputName\": \"nombre\",\n  \"outputType\": \"STRING\"\n}";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {
        DefaultDimension defaultDimension = new DefaultDimension("name", "nombre", null);

        String actualJSON = objectMapper.writeValueAsString(defaultDimension);


        String expectedJSONString = "{\n  \"type\": \"default\",\n  \"dimension\": \"name\",\n  \"outputName\": \"nombre\"\n}";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissingFields() {
        DefaultDimension defaultDimension = new DefaultDimension(null, "nombre",
                OutputType.STRING);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testPutputNameMissingFields() throws JsonProcessingException, JSONException {
        DefaultDimension defaultDimension = new DefaultDimension("name", null, OutputType.STRING);
    }

}
