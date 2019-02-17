package in.zapr.druid.druidry.dimension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class FilteredDimensionTest {
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


    @Test
    public void testListFilteredDimension() throws JsonProcessingException, JSONException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();

        ListFilteredDimension listFilteredDimension = ListFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .values(Arrays.asList("compute.googleapis.com/cores`1"))
                .whitelist(true)
                .build();

        String jsonOutput = objectMapper.writeValueAsString(listFilteredDimension);
        String expectedJSONString = "{\n" +
                "      \"type\": \"listFiltered\",\n" +
                "      \"delegate\": {\n" +
                "        \"type\": \"default\",\n" +
                "        \"dimension\": \"system_label_values\",\n" +
                "        \"outputName\": \"system_label_values\"\n" +
                "      },\n" +
                "      \"values\": [\"compute.googleapis.com/cores`1\"],\n" +
                "      \"isWhitelist\":true\n" +
                "    }";
        JSONAssert.assertEquals(expectedJSONString, jsonOutput, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testListFilteredDimensionIsWhiteListedFalse() throws JsonProcessingException, JSONException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();

        ListFilteredDimension listFilteredDimension = ListFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .values(Arrays.asList("compute.googleapis.com/cores`1"))
                .whitelist(false)
                .build();

        String jsonOutput = objectMapper.writeValueAsString(listFilteredDimension);
        String expectedJSONString = "{\n" +
                "      \"type\": \"listFiltered\",\n" +
                "      \"delegate\": {\n" +
                "        \"type\": \"default\",\n" +
                "        \"dimension\": \"system_label_values\",\n" +
                "        \"outputName\": \"system_label_values\"\n" +
                "      },\n" +
                "      \"values\": [\"compute.googleapis.com/cores`1\"],\n" +
                "      \"isWhitelist\":false\n" +
                "    }";
        JSONAssert.assertEquals(expectedJSONString, jsonOutput, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testListFilteredDimensionIsWhiteListedDefault() throws JsonProcessingException, JSONException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();

        ListFilteredDimension listFilteredDimension = ListFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .values(Arrays.asList("compute.googleapis.com/cores`1"))
                .build();

        String jsonOutput = objectMapper.writeValueAsString(listFilteredDimension);
        String expectedJSONString = "{\n" +
                "      \"type\": \"listFiltered\",\n" +
                "      \"delegate\": {\n" +
                "        \"type\": \"default\",\n" +
                "        \"dimension\": \"system_label_values\",\n" +
                "        \"outputName\": \"system_label_values\"\n" +
                "      },\n" +
                "      \"values\": [\"compute.googleapis.com/cores`1\"]\n" +
                "    }";
        JSONAssert.assertEquals(expectedJSONString, jsonOutput, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testListFilteredDimensionWithNullDimension() throws JsonProcessingException {
        ListFilteredDimension listFilteredDimension = ListFilteredDimension.builder()
                .values(Arrays.asList("compute.googleapis.com/cores`1"))
                .build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testListFilteredDimensionWithNullList() throws JsonProcessingException {
        DimensionSpec dimensionSpec = DefaultDimension.builder()
                .dimension("system_label_values")
                .outputName("system_label_values")
                .build();

        ListFilteredDimension listFilteredDimension = ListFilteredDimension.builder()
                .dimensionSpec(dimensionSpec)
                .build();
    }

}