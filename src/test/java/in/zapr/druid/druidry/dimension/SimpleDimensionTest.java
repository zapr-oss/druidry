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
