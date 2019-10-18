package in.zapr.druid.druidry.having;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.filter.AndFilter;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.SelectorFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
public class AndHavingTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test(enabled=false)
    public void testAllFields() throws JSONException, JsonProcessingException {

        EqualToHaving equalToHaving1 = new EqualToHaving("Hello", "World");
        EqualToHaving equalToHaving2 = new EqualToHaving("Peace", 15);

        AndHaving having = new AndHaving(Arrays.asList(equalToHaving1, equalToHaving2));

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
        jsonObject.put("type", "and");
        jsonObject.put("havingSpecs", filterJsonArray);

        String actualJSON = objectMapper.writeValueAsString(having);
        String expectedJSON = jsonObject.toString();
        System.out.println(actualJSON);
        System.out.println(expectedJSON);

        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldsMissing() {
        AndHaving andHaving = new AndHaving(null);
    }

    @Test
    public void testEquals() {
        DruidHaving equalToHaving1 = new EqualToHaving("Hello", "World");
        DruidHaving equalToHaving2 = new EqualToHaving("Hello", "World");

        Assert.assertEquals(equalToHaving1, equalToHaving2);
    }

}

