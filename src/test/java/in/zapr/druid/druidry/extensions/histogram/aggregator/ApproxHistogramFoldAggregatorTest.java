/*
 * Copyright 2018-present Red Brick Lane Marketing Solutions Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.zapr.druid.druidry.extensions.histogram.aggregator;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.extensions.histogram.aggregator.ApproxHistogramFoldAggregator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApproxHistogramFoldAggregatorTest {

  private static ObjectMapper objectMapper;

  @BeforeClass
  public void init() {
    objectMapper = new ObjectMapper();
  }

  @Test
  public void testAllFields() throws JsonProcessingException, JSONException {

    ApproxHistogramFoldAggregator approxHistogramFoldAgg =
        ApproxHistogramFoldAggregator.builder().name("histogram").fieldName("_loadtime")
            .resolution(100).lowerLimit(-2.50F).upperLimit(2.50F).numberOfBuckets(10).build();

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("type", "approxHistogramFold");
    jsonObject.put("name", "histogram");
    jsonObject.put("fieldName", "_loadtime");
    jsonObject.put("resolution", 100);
    jsonObject.put("lowerLimit", -2.50F);
    jsonObject.put("upperLimit", 2.50F);
    jsonObject.put("numBuckets", 10);

    String actualJSON = objectMapper.writeValueAsString(approxHistogramFoldAgg);
    String expectedJSON = jsonObject.toString();
    JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void testNullName() throws JsonProcessingException, JSONException {
    ApproxHistogramFoldAggregator approxHistogramFoldAgg = ApproxHistogramFoldAggregator.builder()
        .name(null).fieldName("_loadtime").resolution(100).lowerLimit(Float.NEGATIVE_INFINITY)
        .upperLimit(Float.POSITIVE_INFINITY).numberOfBuckets(10).build();

  }

  @Test(expectedExceptions = NullPointerException.class)
  public void testNullFieldName() throws JsonProcessingException, JSONException {
    ApproxHistogramFoldAggregator approxHistogramFoldAgg =
        ApproxHistogramFoldAggregator.builder().name("druidry").fieldName(null).resolution(100)
            .lowerLimit(Float.NEGATIVE_INFINITY).upperLimit(Float.POSITIVE_INFINITY).build();
  }

}
