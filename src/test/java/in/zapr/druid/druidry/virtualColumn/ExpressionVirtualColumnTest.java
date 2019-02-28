package in.zapr.druid.druidry.virtualColumn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.dimension.enums.OutputType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpressionVirtualColumnTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {
        ExpressionVirtualColumn column = new ExpressionVirtualColumn("foo", "a + b", OutputType.LONG);
        JSONObject expected = new JSONObject();
        expected.put("type", "expression");
        expected.put("name", "foo");
        expected.put("outputType", "LONG");
        expected.put("expression", "a + b");
        String actualJSON = objectMapper.writeValueAsString(column);
        JSONAssert.assertEquals(expected.toString(), actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testRequiredFields() throws JsonProcessingException, JSONException {
        ExpressionVirtualColumn column = new ExpressionVirtualColumn("foo", "a + b", null);
        JSONObject expected = new JSONObject();
        expected.put("type", "expression");
        expected.put("name", "foo");
        expected.put("expression", "a + b");
        String actualJSON = objectMapper.writeValueAsString(column);
        JSONAssert.assertEquals(expected.toString(), actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNameMissingFields() {
        new ExpressionVirtualColumn(null, "a + b", null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testExpressionMissingFields() {
        new ExpressionVirtualColumn("foo", null, null);
    }
}
