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

package in.zapr.druid.druidry.query.config;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class IntervalTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingStartField() {

        Temporal startTime = LocalDateTime.now();
        new Interval(startTime, null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMissingEndField() {
        Temporal endTime = LocalDateTime.now();
        new Interval(null, endTime);
    }

    @Test
    public void intervalEqualityTest() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusDays(1);

        Interval interval1 = new Interval(startTime, endTime);
        Interval interval2 = new Interval(startTime, endTime);
        Assert.assertEquals(interval1, interval2);

        Interval interval3 = new Interval(startTime, endTime.plusDays(1));

        Assert.assertNotEquals(interval1, interval3);

        Temporal otherStartTime = LocalDateTime.from(startTime);
        Temporal otherEndTime = LocalDateTime.from(endTime);

        Interval interval4 = new Interval(otherStartTime, otherEndTime);
        Assert.assertEquals(interval1, interval4);
    }
}
