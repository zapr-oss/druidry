package in.zapr.druid.druidry.granularity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
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
        PeriodGranularity spec = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "period");
        jsonObject.put("period", PERIOD);
        jsonObject.put("timeZone", TIMEZONE);
        jsonObject.put("origin", originDate);

        DurationGranularity spec1 = new DurationGranularity(3141, originDate);

        String actualJSON = objectMapper.writeValueAsString(spec);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }
}
