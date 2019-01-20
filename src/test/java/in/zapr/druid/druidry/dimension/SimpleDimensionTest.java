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
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.dimension.enums.OutputType;

public class SimpleDimensionTest {

    private ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {
        SimpleDimension simpleDimension = new SimpleDimension("name");

        String actualString = objectMapper.writeValueAsString(simpleDimension);
        String expectedString = "\"name\"";

        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void testEqualsPositive() {
        SimpleDimension dimension1 = new SimpleDimension("name");
        SimpleDimension dimension2 = new SimpleDimension("name");

        Assert.assertEquals(dimension1, dimension2);
    }

    @Test
    public void testEqualsNegative() {
        SimpleDimension dimension1 = new SimpleDimension("name");
        SimpleDimension dimension2 = new SimpleDimension("name1");

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
