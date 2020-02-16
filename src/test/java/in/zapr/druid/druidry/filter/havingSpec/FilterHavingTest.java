package in.zapr.druid.druidry.filter.havingSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.filter.SelectorFilter;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FilterHavingTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLongField() throws JSONException, JsonProcessingException {
        DruidHaving filter = new FilterHaving(new SelectorFilter("Hello", 1488498926000L));
        JSONObject filterJsonObject = new JSONObject();
        filterJsonObject.put("type", "selector");
        filterJsonObject.put("dimension", "Hello");
        filterJsonObject.put("value", 1488498926000L);

        JSONObject havingJsonObject = new JSONObject();
        havingJsonObject.put("type", "filter");
        havingJsonObject.put("filter", filterJsonObject);

        String actualJSON = objectMapper.writeValueAsString(filter);
        String expectedJSON = havingJsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testDimensionMissing() {
        String dimension = null;
        DruidHaving filter = new FilterHaving(null);
    }
}

