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

package in.zapr.druid.druidry.query.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContextTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testNoFields() throws JsonProcessingException, JSONException {
        Context context = Context.builder().build();

        JSONObject jsonObject = new JSONObject();

        String actualJSON = objectMapper.writeValueAsString(context);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {
        Context context = Context.builder()
                .timeoutInMilliSeconds(1000L)
                .priority(0)
                .queryId("p0-query")
                .useCache(true)
                .populateCache(true)
                .useResultLevelCache(false)
                .populateResultLevelCache(false)
                .bySegment(true)
                .finalize(true)
                .chunkPeriod("P1M")
                .maxScatterGatherBytes(1024L)
                .maxQueuedBytes(1024L)
                .serializeDateTimeAsLong(true)
                .serializeDateTimeAsLongInner(true)
                .minTopNThreshold(10)
                .skipEmptyBuckets(true)
                .grandTotal(true)
                .maxMergingDictionarySize(65536L)
                .maxOnDiskStorage(262144L)
                .groupByStrategy(GroupByStrategy.STRATEGY_V2)
                .groupByIsSingleThreaded(false)
                .bufferGrouperInitialBuckets(1024)
                .bufferGrouperMaxLoadFactor(1.024F)
                .forceHashAggregation(true)
                .intermediateCombineDegree(8)
                .numParallelCombineThreads(4)
                .sortByDimsFirst(false)
                .forceLimitPushDown(false)
                .applyLimitPushDown(true)
                .maxIntermediateRows(2048)
                .maxResults(256)
                .useOffheap(false)
                .vectorize(Vectorize.FORCE)
                .vectorSize(1024)
                .build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timeout", 1000L);
        jsonObject.put("priority", 0);
        jsonObject.put("queryId", "p0-query");
        jsonObject.put("useCache", true);
        jsonObject.put("populateCache", true);
        jsonObject.put("useResultLevelCache", false);
        jsonObject.put("populateResultLevelCache", false);
        jsonObject.put("bySegment", true);
        jsonObject.put("finalize", true);
        jsonObject.put("chunkPeriod", "P1M");
        jsonObject.put("maxScatterGatherBytes", 1024L);
        jsonObject.put("maxQueuedBytes", 1024L);
        jsonObject.put("serializeDateTimeAsLong", true);
        jsonObject.put("serializeDateTimeAsLongInner", true);
        jsonObject.put("minTopNThreshold", 10);
        jsonObject.put("skipEmptyBuckets", true);
        jsonObject.put("grandTotal", true);
        jsonObject.put("maxMergingDictionarySize", 65536L);
        jsonObject.put("maxOnDiskStorage", 262144L);
        jsonObject.put("groupByStrategy", "v2");
        jsonObject.put("groupByIsSingleThreaded", false);
        jsonObject.put("bufferGrouperInitialBuckets", 1024);
        jsonObject.put("bufferGrouperMaxLoadFactor", 1.024);
        jsonObject.put("forceHashAggregation", true);
        jsonObject.put("intermediateCombineDegree", 8);
        jsonObject.put("numParallelCombineThreads", 4);
        jsonObject.put("sortByDimsFirst", false);
        jsonObject.put("forceLimitPushDown", false);
        jsonObject.put("applyLimitPushDown", true);
        jsonObject.put("maxIntermediateRows", 2048);
        jsonObject.put("maxResults", 256);
        jsonObject.put("useOffheap", false);
        jsonObject.put("vectorize", "force");
        jsonObject.put("vectorSize", 1024);

        String actualJSON = objectMapper.writeValueAsString(context);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testContextEquals() {
        Context context1 = Context.builder()
                .timeoutInMilliSeconds(3141L)
                .priority(1)
                .queryId("How are you?")
                .useCache(true)
                .populateCache(true)
                .bySegment(true)
                .finalize(true)
                .chunkPeriod("P1M")
                .minTopNThreshold(1407)
                .maxResults(1611)
                .maxIntermediateRows(1103)
                .groupByIsSingleThreaded(true)
                .build();

        Context context2 = Context.builder()
                .timeoutInMilliSeconds(3141L)
                .priority(1)
                .queryId("How are you?")
                .useCache(true)
                .populateCache(true)
                .bySegment(true)
                .finalize(true)
                .chunkPeriod("P1M")
                .minTopNThreshold(1407)
                .maxResults(1611)
                .maxIntermediateRows(1103)
                .groupByIsSingleThreaded(true)
                .build();

        Assert.assertEquals(context1, context2);
    }

    @Test
    public void testContextUnequals() {
        Context context1 = Context.builder()
                .timeoutInMilliSeconds(3141L)
                .priority(1)
                .queryId("How are you?")
                .useCache(true)
                .populateCache(true)
                .bySegment(true)
                .finalize(true)
                .chunkPeriod("P1M")
                .minTopNThreshold(1407)
                .maxResults(1611)
                .maxIntermediateRows(1103)
                .groupByIsSingleThreaded(true)
                .build();

        Context context2 = Context.builder()
                .timeoutInMilliSeconds(3141L)
                .priority(1)
                .queryId("How are you?")
                .useCache(true)
                .populateCache(true)
                .bySegment(true)
                .finalize(true)
                .chunkPeriod("P1M")
                .minTopNThreshold(1407)
                .maxResults(1611)
                .maxIntermediateRows(1103)
                // Differentiator
                .groupByIsSingleThreaded(false)
                .build();

        Assert.assertNotEquals(context1, context2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void preconditionCheck() {
        Context context = Context.builder()
                .timeoutInMilliSeconds(-1000L)
                .build();
    }
}