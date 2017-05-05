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

package extractionFunctions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zapr.druidquery.extractionFunctions.PartialExtractionFunction;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PartialExtractionFunctionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        PartialExtractionFunction partialExtractionFunction = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        String actualJSON = objectMapper.writeValueAsString(partialExtractionFunction);

        String expecedJSONString = "{ \"type\" : \"partial\", \"expr\" : \"abcd\" }\n";

        JSONAssert.assertEquals(expecedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testExprField() throws JsonProcessingException, JSONException {
        PartialExtractionFunction partialExtractionFunction = PartialExtractionFunction.builder()
                .build();
    }
}
