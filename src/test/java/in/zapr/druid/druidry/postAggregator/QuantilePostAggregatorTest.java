package in.zapr.druid.druidry.postAggregator;

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
public class QuantilePostAggregatorTest {

	private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

    	QuantilePostAggregator quantilePostAgg = QuantilePostAggregator.builder()
    			.name("quantile")
    			.fieldName("timeAgg")
    			.probability(0.50F)
    			.build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "quantile");
        jsonObject.put("name", "quantile");
        jsonObject.put("fieldName", "timeAgg");
        jsonObject.put("probability", 0.50F);

        String actualJSON = objectMapper.writeValueAsString(quantilePostAgg);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {
    	QuantilePostAggregator quantilePostAgg = QuantilePostAggregator.builder()
    			.name(null)
    			.fieldName("timeAgg")
    			.probability(0.50F)
    			.build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {
    	QuantilePostAggregator quantilePostAgg = QuantilePostAggregator.builder()
    			.name("quantile")
    			.fieldName(null)
    			.probability(0.50F)
    			.build();
    }

}
