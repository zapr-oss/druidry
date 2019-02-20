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
    public void testNullName() {
        CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
            .name(null).fieldName("_loadtime").breaks(breaks).build();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testNullFieldName() {
        CustomBucketsPostAggregator customBucketsPostAgg = CustomBucketsPostAggregator.builder()
            .name("custom_buckets").fieldName(null).breaks(breaks).build();
    }
}