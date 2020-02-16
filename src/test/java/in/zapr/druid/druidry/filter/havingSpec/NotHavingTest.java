package in.zapr.druid.druidry.filter.havingSpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NotHavingTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        DruidHaving equalToHaving1 = new EqualToHaving("Hello", "World");

        DruidHaving having = new NotHaving(equalToHaving1);

        JSONObject havingInner = new JSONObject();
        havingInner.put("type", "equalTo");
        havingInner.put("aggregation", "Hello");
        havingInner.put("value", "World");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "not");
        jsonObject.put("havingSpec", havingInner);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldsMissing() {
        NotHaving andHaving = new NotHaving(null);
    }
}
