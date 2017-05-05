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
import com.zapr.druidquery.extractionFunctions.TimeParsingExtractionFunction;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

public class TimeParsingExtractionFunctionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat resultFormat = new SimpleDateFormat("MM-dd-yyyy");

        TimeParsingExtractionFunction timeParsingTestExtractionFunction = TimeParsingExtractionFunction.builder()
                .timeFormat(timeFormat)
                .resultFormat(resultFormat)
                .build();

        String actualJSON = objectMapper.writeValueAsString(timeParsingTestExtractionFunction);

        String expectedJSONString = "{ \"type\" : \"time\",\n  \"timeFormat\" : \"dd-MM-yyyy\",\n  \"resultFormat\" : \"MM-dd-yyyy\" }";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testTimeFormatField() {
        SimpleDateFormat resultFormat = new SimpleDateFormat("MM-dd-yyyy");
        TimeParsingExtractionFunction timeParsingTestExtractionFunction = TimeParsingExtractionFunction.builder()
                .resultFormat(resultFormat)
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRequiredField() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd-yyyy");
        TimeParsingExtractionFunction timeParsingTestExtractionFunction = TimeParsingExtractionFunction.builder()
                .timeFormat(timeFormat)
                .build();
    }


}
