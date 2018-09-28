package in.zapr.druid.druidry.postAggregator;

import java.util.ArrayList;
import java.util.List;

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
public class CustomBucketsPostAggregatorTest {

	private static ObjectMapper objectMapper;
	private final List<Float> breaks = new ArrayList<>();

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    	breaks.add(0.50F);
    	breaks.add(0.90F);
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

    	CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
    			.name("histogram")
    			.fieldName("_loadtime")
    			.breaks(breaks)
    			.build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "customBuckets");
        jsonObject.put("name", "custom_buckets");
        jsonObject.put("fieldName", "_loadtime");
        jsonObject.put("breaks", breaks);

        String actualJSON = objectMapper.writeValueAsString(customBucketsPostAgg);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {
    	CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
    			.name(null)
    			.fieldName("_loadtime")
    			.breaks(breaks)
    			.build();

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() throws JsonProcessingException, JSONException {
    	CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
    			.name("histogram")
    			.fieldName(null)
    			.breaks(breaks)
    			.build();
    }

}
