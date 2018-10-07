package in.zapr.druid.druidry.extensions.histogram;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.zapr.druid.druidry.extensions.histogram.QuantilesPostAggregator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuantilesPostAggregatorTest {

	private static ObjectMapper objectMapper;
	private final Set<Float> probabilities = new HashSet<>();

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
        probabilities.add(0.50F);
        probabilities.add(0.90F);
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

    	QuantilesPostAggregator quantilesPostAgg = QuantilesPostAggregator.builder()
    			.name("quantiles")
    			.fieldName("timeAgg")
    			.probabilities(probabilities)
    			.build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "quantiles");
        jsonObject.put("name", "quantiles");
        jsonObject.put("fieldName", "timeAgg");
        JsonNode listNode = objectMapper.valueToTree(probabilities);
        final JSONArray jsonArr = new JSONArray(listNode.toString());
        jsonObject.put("probabilities", jsonArr);
        String actualJSON = objectMapper.writeValueAsString(quantilesPostAgg);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {
    	QuantilesPostAggregator quantilesPostAgg = QuantilesPostAggregator.builder()
    			.name(null)
    			.fieldName("timeAgg")
    			.probabilities(probabilities)
    			.build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {
    	QuantilesPostAggregator quantilesPostAgg = QuantilesPostAggregator.builder()
    			.name("quantiles")
    			.fieldName(null)
    			.probabilities(probabilities)
    			.build();
    }

}
