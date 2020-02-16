package in.zapr.druid.druidry.having;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class OrHavingTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        DruidHaving equalToHaving1 = new EqualToHaving("Hello", "World");
        DruidHaving equalToHaving2 = new EqualToHaving("Peace", 15);

        DruidHaving having = new OrHaving(Arrays.asList(equalToHaving1, equalToHaving2));

        JSONObject having1 = new JSONObject();
        having1.put("type", "equalTo");
        having1.put("aggregation", "Hello");
        having1.put("value", "World");

        JSONObject having2 = new JSONObject();
        having2.put("type", "equalTo");
        having2.put("aggregation", "Peace");
        having2.put("value", 15);

        JSONArray filterJsonArray = new JSONArray(Arrays.asList(having1, having2));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "or");
        jsonObject.put("havingSpecs", filterJsonArray);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();

        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldsMissing() {
        OrHaving andHaving = new OrHaving(null);
    }
}
