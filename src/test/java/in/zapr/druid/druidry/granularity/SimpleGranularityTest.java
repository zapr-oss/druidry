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

package in.zapr.druid.druidry.granularity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        DateTime originDate = new DateTime(DateTimeZone.UTC);
        DurationGranularity granularity2 = new DurationGranularity(3141, originDate);

        Assert.assertNotEquals(granularity1, granularity2);
    }
}
