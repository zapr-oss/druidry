package in.zapr.druid.druidry.dimension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PrefixFilteredDimensionTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testPreFixFilteredDimension() throws JsonProcessingException, JSONException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();
        PrefixFilteredDimension prefixFilteredDimension = PrefixFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .prefix("compute.googleapis.com/cores")
                .build();

        String jsonOutput = objectMapper.writeValueAsString(prefixFilteredDimension);
        String expectedJSONString = "{\n" +
                "      \"type\": \"prefixFiltered\",\n" +
                "      \"delegate\": {\n" +
                "        \"type\": \"default\",\n" +
                "        \"dimension\": \"system_label_values\",\n" +
                "        \"outputName\": \"system_label_values\"\n" +
                "      },\n" +
                "      \"prefix\": \"compute.googleapis.com/cores\"\n" +
                "    }";
        JSONAssert.assertEquals(expectedJSONString, jsonOutput, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testPreFixFilteredDimensionWithNullDimensionSpec() throws JsonProcessingException {
        PrefixFilteredDimension prefixFilteredDimension = PrefixFilteredDimension.builder()
                .prefix("compute.googleapis.com/cores")
                .build();

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testPreFixFilteredDimensionWithNullPrefix() throws JsonProcessingException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();

        PrefixFilteredDimension prefixFilteredDimension = PrefixFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .build();
    }


}