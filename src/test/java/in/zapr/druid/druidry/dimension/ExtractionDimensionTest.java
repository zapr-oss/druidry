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
import in.zapr.druid.druidry.extractionFunctions.ExtractionFunction;
import in.zapr.druid.druidry.extractionFunctions.PartialExtractionFunction;

public class ExtractionDimensionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSampleExtractionFunction() throws JSONException, JsonProcessingException {

        ExtractionFunction partialExtractionFunction = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension extractionDimension = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .outputType(OutputType.FLOAT)
                .extractionFunction(partialExtractionFunction)
                .build();

        String actualJSON = objectMapper.writeValueAsString(extractionDimension);

        String expectedJSONString = "\n{\n  \"type\" : \"extraction\",\n  " +
                "\"dimension\" : \"name\",\n  \"outputName\" :  \"nombre\",\n " +
                " \"outputType\": \"FLOAT\",\n  " +
                "\"extractionFn\" : { \"type\" : \"partial\", \"expr\" : \"abcd\" }\n}\n\n";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {
        ExtractionFunction partialExtractionFunction = PartialExtractionFunction.builder().expr("abcd").build();

        ExtractionDimension extractionDimension = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .extractionFunction(partialExtractionFunction)
                .build();

        String actualJSON = objectMapper.writeValueAsString(extractionDimension);

        String expectedJSONString = "\n{\n  \"type\" : \"extraction\",\n  " +
                "\"dimension\" : \"name\",\n  \"outputName\" :  \"nombre\",\n  " +
                "\"extractionFn\" : { \"type\" : \"partial\", \"expr\" : \"abcd\" }\n}\n\n";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissingFields() {
        ExtractionFunction partialExtractionFunction = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension extractionDimension = ExtractionDimension.builder()
                .outputName("nombre")
                .extractionFunction(partialExtractionFunction)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOutputNameMissingFields() {
        ExtractionFunction partialExtractionFunction = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension extractionDimension = ExtractionDimension.builder()
                .dimension("name")
                .extractionFunction(partialExtractionFunction)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testExtractionFunctionMissingFields() {

        ExtractionDimension extractionDimension = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .build();
    }

    @Test
    public void testEqualsPositive() {
        ExtractionFunction partialExtractionFunction1 = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension dimension1 = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .outputType(OutputType.FLOAT)
                .extractionFunction(partialExtractionFunction1)
                .build();

        ExtractionFunction partialExtractionFunction2 = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension dimension2 = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .outputType(OutputType.FLOAT)
                .extractionFunction(partialExtractionFunction2)
                .build();

        Assert.assertEquals(dimension1, dimension2);
    }

    @Test
    public void testEqualsNegative() {
        ExtractionFunction partialExtractionFunction1 = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension dimension1 = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .outputType(OutputType.LONG)
                .extractionFunction(partialExtractionFunction1)
                .build();

        ExtractionFunction partialExtractionFunction2 = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension dimension2 = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .outputType(OutputType.FLOAT)
                .extractionFunction(partialExtractionFunction2)
                .build();

        Assert.assertNotEquals(dimension1, dimension2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        SimpleDimension dimension1 = new SimpleDimension("name");
        ExtractionFunction partialExtractionFunction1 = PartialExtractionFunction.builder()
                .expr("abcd")
                .build();

        ExtractionDimension dimension2 = ExtractionDimension.builder()
                .dimension("name")
                .outputName("nombre")
                .outputType(OutputType.LONG)
                .extractionFunction(partialExtractionFunction1)
                .build();

        Assert.assertNotEquals(dimension1, dimension2);
    }
}