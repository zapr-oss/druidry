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
import in.zapr.druid.druidry.extractionFunctions.CascadeExtractionFunction;
import in.zapr.druid.druidry.extractionFunctions.ExtractionFunction;
import in.zapr.druid.druidry.extractionFunctions.PartialExtractionFunction;
import in.zapr.druid.druidry.extractionFunctions.StrLenExtractionFunction;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CascadeExtractionFunctionTest {
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

        StrLenExtractionFunction strLenExtractionFunction = StrLenExtractionFunction.builder().build();


        List<ExtractionFunction> extractionFunctions = new ArrayList<>();
        extractionFunctions.add(partialExtractionFunction);
        extractionFunctions.add(strLenExtractionFunction);

        CascadeExtractionFunction cascadeExtractionFunction = CascadeExtractionFunction.builder()
                .extractionFns(extractionFunctions)
                .build();

        String actualJSON = objectMapper.writeValueAsString(cascadeExtractionFunction);

        String expecedJSONString = "{\n  \"type\" : \"cascade\", \n  \"extractionFns\": [\n    { \"type\" : \"partial\", \"expr\" : \"abcd\" },\n    { \n      \"type\" : \"strlen\" \n    }\n  ]\n}";
        JSONAssert.assertEquals(expecedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testExtractionFunctionsFields() {
        CascadeExtractionFunction cascadeExtractionFunction = CascadeExtractionFunction.builder().build();
    }
}