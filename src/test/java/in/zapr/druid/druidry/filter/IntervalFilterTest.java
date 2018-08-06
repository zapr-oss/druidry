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

package in.zapr.druid.druidry.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import in.zapr.druid.druidry.Interval;

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

        DateTime startTimeInterval1 = new DateTime(2013, 8, 31,
                0, 0, 0, DateTimeZone.UTC);
        DateTime endTimeInterval1 = new DateTime(2013, 9, 3,
                0, 0, 0, DateTimeZone.UTC);

        DateTime startTimeInterval2 = new DateTime(2018, 8, 31,
                0, 0, 0, DateTimeZone.UTC);
        DateTime endTimeInterval2 = new DateTime(2018, 9, 3,
                0, 0, 0, DateTimeZone.UTC);

        Interval interval1 = new Interval(startTimeInterval1, endTimeInterval1);
        Interval interval2 = new Interval(startTimeInterval2, endTimeInterval2);

        IntervalFilter intervalFilter
                = new IntervalFilter("__time", Arrays.asList(interval1, interval2));

        String actualJSON = objectMapper.writeValueAsString(intervalFilter);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }
}
