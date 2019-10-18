package in.zapr.druid.druidry.having;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DimSelectionHavingTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFieldsNumeric() throws JSONException, JsonProcessingException {
        DruidHaving having = new DimSelectorHaving("dimension_name", 2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", 2);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFieldsString() throws JSONException, JsonProcessingException {
        DruidHaving having = new DimSelectorHaving("dimension_name", "2");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dimSelector");
        jsonObject.put("dimension", "dimension_name");
        jsonObject.put("value", "2");

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSettingRequiredFieldAsNull() {
        DimSelectorHaving having = new DimSelectorHaving(null, "World");
    }
}