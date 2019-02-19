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
    public void testGeneratedJSON() throws JsonProcessingException, JSONException {
        ExpressionVirtualColumn column = new ExpressionVirtualColumn("foo", "a + b");
        JSONObject expected = new JSONObject();
        expected.put("type", "expression");
        expected.put("name", "foo");
        expected.put("outputType", "FLOAT");
        expected.put("expression", "a + b");
        String output = objectMapper.writeValueAsString(column);
        JSONAssert.assertEquals(expected.toString(), output, JSONCompareMode.NON_EXTENSIBLE);

        column = new ExpressionVirtualColumn("bar", "a + b", OutputType.LONG);
        expected = new JSONObject();
        expected.put("type", "expression");
        expected.put("name", "bar");
        expected.put("outputType", "LONG");
        expected.put("expression", "a + b");
        output = objectMapper.writeValueAsString(column);
        JSONAssert.assertEquals(expected.toString(), output, JSONCompareMode.NON_EXTENSIBLE);
    }
}
