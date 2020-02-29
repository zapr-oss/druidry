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

package in.zapr.druid.druidry.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.query.config.Interval;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;

public class IntervalFilterTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingDimensionField() {

        IntervalFilter intervalFilter = new IntervalFilter(null, new ArrayList<>());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingIntervalField() {

        IntervalFilter intervalFilter = new IntervalFilter("__time", null);
    }

    @Test
    public void testFields() throws JsonProcessingException, JSONException {

        JSONArray intervalJsonArray
                = new JSONArray(Arrays.asList("2013-08-31T00:00:00.000Z/2013-09-03T00:00:00.000Z",
                "2018-08-31T00:00:00.000Z/2018-09-03T00:00:00.000Z"));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "interval");
        jsonObject.put("dimension", "__time");
        jsonObject.put("intervals", intervalJsonArray);

        Temporal startTimeInterval1 = ZonedDateTime.of(2013, 8, 31,
                                              0, 0, 0, 0, ZoneOffset.UTC);
        Temporal endTimeInterval1 = ZonedDateTime.of(2013, 9, 3,
                                            0, 0, 0, 0, ZoneOffset.UTC);

        Temporal startTimeInterval2 = ZonedDateTime.of(2018, 8, 31,
                                              0, 0, 0, 0, ZoneOffset.UTC);
        Temporal endTimeInterval2 = ZonedDateTime.of(2018, 9, 3,
                                            0, 0, 0, 0, ZoneOffset.UTC);

        Interval interval1 = new Interval(startTimeInterval1, endTimeInterval1);
        Interval interval2 = new Interval(startTimeInterval2, endTimeInterval2);

        IntervalFilter intervalFilter
                = new IntervalFilter("__time", Arrays.asList(interval1, interval2));

        String actualJSON = objectMapper.writeValueAsString(intervalFilter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }
}
