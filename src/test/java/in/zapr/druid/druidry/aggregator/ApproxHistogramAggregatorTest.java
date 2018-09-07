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

    	ApproxHistogramAggregator approxHistogramAgg = ApproxHistogramAggregator.builder()
    			.name("histogram")
    			.fieldName("_loadtime")
    			.resolution(100)
    			.lowerLimit("-Infinity")
    			.upperLimit("+Infinity")
    			.numberOfBuckets(10)
    			.build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "approxHistogram");
        jsonObject.put("name", "histogram");
        jsonObject.put("fieldName", "_loadtime");
        jsonObject.put("resolution", 100);
        jsonObject.put("lowerLimit", "-Infinity");
        jsonObject.put("upperLimit", "+Infinity");
        jsonObject.put("numberOfBuckets", 10);

        String actualJSON = objectMapper.writeValueAsString(approxHistogramAgg);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {
    	ApproxHistogramAggregator approxHistogramAgg = ApproxHistogramAggregator.builder()
    			.name(null)
    			.fieldName("_loadtime")
    			.resolution(100)
    			.lowerLimit("-Infinity")
    			.upperLimit("+Infinity")
    			.numberOfBuckets(10)
    			.build();

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {
    	ApproxHistogramAggregator approxHistogramAgg = ApproxHistogramAggregator.builder()
    			.name("druidry")
    			.fieldName(null)
    			.resolution(100)
    			.lowerLimit("-Infinity")
    			.upperLimit("+Infinity")
    			.build();
    }

}
