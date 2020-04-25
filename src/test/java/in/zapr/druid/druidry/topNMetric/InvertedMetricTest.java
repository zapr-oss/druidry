package in.zapr.druid.druidry.topNMetric;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
public class InvertedMetricTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getInvertedMetricJSON() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "inverted");
        jsonObject.put("metric", "count");
        return jsonObject;
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        InvertedMetric invertedMetric = new InvertedMetric(new SimpleMetric("count"));

        JSONObject jsonObject = getInvertedMetricJSON();

        String actualJSON = objectMapper.writeValueAsString(invertedMetric);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);;
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testMetricMissingFields() {

        InvertedMetric invertedMetric = new InvertedMetric(null);
    }

    @Test
    public void testEqualsPositive() {

        InvertedMetric invertedMetric1 = new InvertedMetric(new SimpleMetric("count"));
        InvertedMetric invertedMetric2 = new InvertedMetric(new SimpleMetric("count"));

        Assert.assertEquals(invertedMetric1, invertedMetric2);
    }

    @Test
    public void testEqualsNegative() {

        InvertedMetric invertedMetric1 = new InvertedMetric(new SimpleMetric("sum"));
        InvertedMetric invertedMetric2 = new InvertedMetric(new SimpleMetric("count"));

        Assert.assertNotEquals(invertedMetric1, invertedMetric2);
    }
}
