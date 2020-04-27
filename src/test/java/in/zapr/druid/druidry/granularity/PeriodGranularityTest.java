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

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PeriodGranularityTest {
    private final static String PERIOD = "PT1H";
    private final static String TIMEZONE = "Asia/Kolkata";
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        ZonedDateTime originDate = ZonedDateTime.now(ZoneId.of(TIMEZONE));
        PeriodGranularity periodGranularity = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(ZoneId.of(TIMEZONE))
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "period");
        jsonObject.put("period", PERIOD);
        jsonObject.put("timeZone", TIMEZONE);
        jsonObject.put("origin", ISO_DATE_TIME.format(originDate));

        String actualJSON = objectMapper.writeValueAsString(periodGranularity);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testEqualsPositive() {
        ZonedDateTime originDate = ZonedDateTime.now(ZoneId.of(TIMEZONE));
        PeriodGranularity granularity1 = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(ZoneId.of(TIMEZONE))
                .build();

        ZonedDateTime originDate2 = ZonedDateTime.from(originDate);
        PeriodGranularity granularity2 = PeriodGranularity.builder()
                .origin(originDate2)
                .period(PERIOD)
                .timeZone(ZoneId.of(TIMEZONE))
                .build();

        Assert.assertEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsNegative() {
        ZonedDateTime originDate = ZonedDateTime.now(ZoneId.of(TIMEZONE));
        PeriodGranularity granularity1 = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(ZoneId.of(TIMEZONE))
                .build();

        ZonedDateTime originDate2 = ZonedDateTime.from(originDate);
        originDate2 = originDate2.plusDays(1);
        PeriodGranularity granularity2 = PeriodGranularity.builder()
                .origin(originDate2)
                .period(PERIOD)
                .timeZone(ZoneId.of(TIMEZONE))
                .build();

        Assert.assertNotEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        SimpleGranularity granularity1 = new SimpleGranularity(PredefinedGranularity.ALL);

        ZonedDateTime originDate = ZonedDateTime.now(ZoneId.of(TIMEZONE));
        PeriodGranularity granularity2 = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(ZoneId.of(TIMEZONE))
                .build();

        Assert.assertNotEquals(granularity1, granularity2);
    }
}
