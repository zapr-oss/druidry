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

package in.zapr.druid.druidry.extensions.histogram.postAggregator;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.ArraySizeComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.zapr.druid.druidry.extensions.histogram.postAggregator.CustomBucketsPostAggregator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomBucketsPostAggregatorTest {

  private static ObjectMapper objectMapper;
  private final Set<Float> breaks = new HashSet<>();

  @BeforeClass
  public void init() {
    objectMapper = new ObjectMapper();
    breaks.add(0.50F);
    breaks.add(0.90F);
  }

  @Test
  public void testAllFields() throws JsonProcessingException, JSONException {

    CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
        .name("custom_buckets").fieldName("_loadtime").breaks(breaks).build();

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("type", "customBuckets");
    jsonObject.put("name", "custom_buckets");
    jsonObject.put("fieldName", "_loadtime");
    JsonNode listNode = objectMapper.valueToTree(breaks);
    final JSONArray jsonArr = new JSONArray(listNode.toString());
    jsonObject.put("breaks", jsonArr);
    String actualJSON = objectMapper.writeValueAsString(customBucketsPostAgg);
    String expectedJSON = jsonObject.toString();
    JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
  }

  @Test(expectedExceptions = NullPointerException.class)
  public void testNullName() throws JsonProcessingException, JSONException {
    CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
        .name(null).fieldName("_loadtime").breaks(breaks).build();

  }

  @Test(expectedExceptions = NullPointerException.class)
  public void testNullFieldName() throws JsonProcessingException, JSONException {
    CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
        .name("custom_buckets").fieldName(null).breaks(breaks).build();
  }

}
