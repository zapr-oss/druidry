/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
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
    jsonObject.put("numberOfBuckets", 10);

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
