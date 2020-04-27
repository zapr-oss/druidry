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

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;

public class SimpleGranularityTest {

    @Test
    public void testEqualsPositive() {
        SimpleGranularity granularity1 = new SimpleGranularity(PredefinedGranularity.ALL);
        SimpleGranularity granularity2 = new SimpleGranularity(PredefinedGranularity.ALL);

        Assert.assertEquals(granularity1, granularity2);
    }

    @Test
    public void testEqualsNegative() {
        SimpleGranularity granularity1 = new SimpleGranularity(PredefinedGranularity.ALL);
        SimpleGranularity granularity2 = new SimpleGranularity(PredefinedGranularity.WEEK);

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
