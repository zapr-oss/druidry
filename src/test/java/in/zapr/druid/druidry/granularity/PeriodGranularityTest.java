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

package in.zapr.druid.druidry.granularity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PeriodGranularityTest {
    private static ObjectMapper objectMapper;
    private final static String PERIOD = "PT1H";
    private final static String TIMEZONE = "Asia/Kolkata";

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        DateTime originDate = new DateTime(DateTimeZone.forID(TIMEZONE));
        PeriodGranularity periodGranularity = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "period");
        jsonObject.put("period", PERIOD);
        jsonObject.put("timeZone", TIMEZONE);
        jsonObject.put("origin", originDate);

        String actualJSON = objectMapper.writeValueAsString(periodGranularity);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testEqualsPositive() {
        DateTime originDate = new DateTime(DateTimeZone.forID(TIMEZONE));
        PeriodGranularity granularity1 = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();

        DateTime originDate2 = new DateTime(originDate);
        PeriodGranularity granularity2 = PeriodGranularity.builder()
                .origin(originDate2)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();

        Assert.assertEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsNegative() {
        DateTime originDate = new DateTime(DateTimeZone.forID(TIMEZONE));
        PeriodGranularity granularity1 = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();

        DateTime originDate2 = new DateTime(DateTimeZone.forID(TIMEZONE));
        originDate2 = originDate2.plusDays(1);
        PeriodGranularity granularity2 = PeriodGranularity.builder()
                .origin(originDate2)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();

        Assert.assertNotEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        SimpleGranularity granularity1 = new SimpleGranularity(PredefinedGranularity.ALL);

        DateTime originDate = new DateTime(DateTimeZone.forID(TIMEZONE));
        PeriodGranularity granularity2 = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();

        Assert.assertNotEquals(granularity1, granularity2);
    }
}
