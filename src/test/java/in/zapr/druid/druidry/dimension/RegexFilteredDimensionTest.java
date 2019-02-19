package in.zapr.druid.druidry.dimension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegexFilteredDimensionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSampleRegexFilteredDimension() throws JsonProcessingException, JSONException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();

        RegexFilteredDimension regexFilteredDimension = RegexFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .pattern("compute.googleapis.com/cores`.*")
                .build();

        String jsonOutput = objectMapper.writeValueAsString(regexFilteredDimension);
        String expectedJSONString = "{\n" +
                "      \"type\": \"regexFiltered\",\n" +
                "      \"delegate\": {\n" +
                "        \"type\": \"default\",\n" +
                "        \"dimension\": \"system_label_values\",\n" +
                "        \"outputName\": \"system_label_values\"\n" +
                "      },\n" +
                "      \"pattern\": \"compute.googleapis.com/cores`.*\"\n" +
                "    }";
        JSONAssert.assertEquals(expectedJSONString, jsonOutput, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSampleRegexFilteredDimensionWithNullDimensionSpec() throws JsonProcessingException {
        RegexFilteredDimension regexFilteredDimension = RegexFilteredDimension.builder()
                .pattern("compute.googleapis.com/cores`.*")
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSampleRegexFilteredDimensionWithNullRegex() throws JsonProcessingException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();
        RegexFilteredDimension regexFilteredDimension = RegexFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .build();
    }


}
