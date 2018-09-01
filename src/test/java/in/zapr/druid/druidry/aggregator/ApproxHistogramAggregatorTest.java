package in.zapr.druid.druidry.aggregator;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApproxHistogramAggregatorTest {

	private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

    	ApproxHistogramAggregator approxHistogramAgg = new ApproxHistogramAggregator("histogram", "_loadtime", 100, "-Infinity", "+Infinity");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "approxHistogram");
        jsonObject.put("name", "histogram");
        jsonObject.put("fieldName", "_loadtime");
        jsonObject.put("resolution", 100);
        jsonObject.put("lowerLimit", "-Infinity");
        jsonObject.put("upperLimit", "+Infinity");

        String actualJSON = objectMapper.writeValueAsString(approxHistogramAgg);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {

    	ApproxHistogramAggregator approxHistogramAgg = new ApproxHistogramAggregator(null, "_loadtime", 100, "-Infinity", "+Infinity");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {

    	ApproxHistogramAggregator approxHistogramAgg = new ApproxHistogramAggregator("histogram", null,100, "-Infinity", "+Infinity");
    }

}
