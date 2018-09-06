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
