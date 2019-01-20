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

package in.zapr.druid.druidry.extractionFunctions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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