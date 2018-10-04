package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import in.zapr.druid.druidry.postAggregator.FieldAccessPostAggregator;

public class ThetaSketchEstimatePostAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    private JSONObject getFieldAccessJSON() throws JSONException {
        JSONObject fieldAccess = new JSONObject();
        fieldAccess.put("type", "fieldAccess");
        fieldAccess.put("fieldName", "stars");

        return fieldAccess;
    }

    @Test
    public void testAllFieldsWithFieldAccess() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", fieldAccessPostAggregator);


        JSONObject fieldAccess = getFieldAccessJSON();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchEstimate");
        jsonObject.put("name", "estimate_stars");
        jsonObject.put("field", fieldAccess);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchEstimatePostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test
    public void testAllFieldsWithThetaSketchSetOp() throws JsonProcessingException, JSONException {

        FieldAccessPostAggregator fieldAccessPostAggregator =
                new FieldAccessPostAggregator("stars");

        ThetaSketchSetOpPostAggregator thetaSketchSetOpPostAggregator = ThetaSketchSetOpPostAggregator.builder()
                .name("name")
                .function(ThetaSketchFunction.INTERSECT)
                .fields(Collections.singletonList(fieldAccessPostAggregator))
                .build();

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", thetaSketchSetOpPostAggregator);


        JSONObject fieldAccess = getFieldAccessJSON();

        JSONArray fields = new JSONArray(Collections.singletonList(getFieldAccessJSON()));

        JSONObject thetaSketchSetOp = new JSONObject();
        thetaSketchSetOp.put("type", "thetaSketchSetOp");
        thetaSketchSetOp.put("name", "name");
        thetaSketchSetOp.put("func", "INTERSECT");
        thetaSketchSetOp.put("fields", fields);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "thetaSketchEstimate");
        jsonObject.put("name", "estimate_stars");
        jsonObject.put("field", thetaSketchSetOp);

        String actualJSON = objectMapper.writeValueAsString(thetaSketchEstimatePostAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() {

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator(null, new FieldAccessPostAggregator("stars"));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullField() {

        ThetaSketchEstimatePostAggregator thetaSketchEstimatePostAggregator =
                new ThetaSketchEstimatePostAggregator("estimate_stars", null);
    }

}
