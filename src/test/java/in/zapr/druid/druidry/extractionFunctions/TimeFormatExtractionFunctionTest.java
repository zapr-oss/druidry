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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Locale;

import in.zapr.druid.druidry.granularity.DurationGranularity;

public class TimeFormatExtractionFunctionTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        String format = "dd-MM-yyyy";
        Locale locale = Locale.FRENCH;

        String timeZone = "America/Montreal";

        DateTime originDate = new DateTime(DateTimeZone.UTC);

        DurationGranularity spec = new DurationGranularity(7200000, originDate);

        TimeFormatExtractionFunction timeFormatExtractonFunction = TimeFormatExtractionFunction.builder()
                .format(format)
                .timeZone(timeZone)
                .locale(locale)
                .granularity(spec)
                .asMillis(true)
                .build();

        String actualJSON = objectMapper.writeValueAsString(timeFormatExtractonFunction);

        String expectedJSONString = "{\n\"type\" : \"timeFormat\",\n \"format\" : \"dd-MM-yyyy\",\n    \"timeZone\" : \"America/Montreal\",\n    \"locale\" : \"fr\",\n    \"granularity\": {\"type\": \"duration\", \"duration\": 7200000, \"origin\": \"" +
                originDate.toDateTimeISO() +
                "\"},\n    \"asMillis\": true\n\n  }";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void TestRequiredFields() throws JsonProcessingException, JSONException {
        TimeFormatExtractionFunction timeFormatExtractionFunction = TimeFormatExtractionFunction.builder().build();

        String actualJSON = objectMapper.writeValueAsString(timeFormatExtractionFunction);
        String expectedJSONString = "{ \"type\" : \"timeFormat\" }\n";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }
}