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

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class DurationGranularityTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        ZonedDateTime originDate = ZonedDateTime.now(ZoneOffset.UTC);
        DurationGranularity granularity = new DurationGranularity(3141, originDate);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "duration");
        jsonObject.put("duration", 3141L);
        jsonObject.put("origin", ISO_DATE_TIME.format(originDate));

        String actualJSON = objectMapper.writeValueAsString(granularity);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JSONException, JsonProcessingException {
        DurationGranularity granularity = new DurationGranularity(3141);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "duration");
        jsonObject.put("duration", 3141L);

        String actualJSON = objectMapper.writeValueAsString(granularity);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testEqualsPositive() {
        DurationGranularity granularity1 = new DurationGranularity(3141);
        DurationGranularity granularity2 = new DurationGranularity(3141);

        Assert.assertEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsNegative() {
        DurationGranularity granularity1 = new DurationGranularity(3141);
        DurationGranularity granularity2 = new DurationGranularity(3140);

        Assert.assertNotEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        SimpleGranularity granularity1 = new SimpleGranularity(PredefinedGranularity.ALL);

        ZonedDateTime originDate = ZonedDateTime.now(ZoneOffset.UTC);
        DurationGranularity granularity2 = new DurationGranularity(3141, originDate);

        Assert.assertNotEquals(granularity1, granularity2);
    }
}
