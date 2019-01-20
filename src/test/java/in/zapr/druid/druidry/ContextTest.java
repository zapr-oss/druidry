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

package in.zapr.druid.druidry;

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
                .timeoutInMilliSeconds(3141)
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

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timeout", 3141);
        jsonObject.put("priority", 1);
        jsonObject.put("queryId", "How are you?");
        jsonObject.put("useCache", true);
        jsonObject.put("populateCache", true);
        jsonObject.put("bySegment", true);
        jsonObject.put("finalize", true);
        jsonObject.put("chunkPeriod", "P1M");
        jsonObject.put("minTopNThreshold", 1407);
        jsonObject.put("maxResults", 1611);
        jsonObject.put("maxIntermediateRows", 1103);
        jsonObject.put("groupByIsSingleThreaded", true);

        String actualJSON = objectMapper.writeValueAsString(context);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void testContextEquals() {
        Context context1 = Context.builder()
                .timeoutInMilliSeconds(3141)
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
                .timeoutInMilliSeconds(3141)
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
                .timeoutInMilliSeconds(3141)
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
                .timeoutInMilliSeconds(3141)
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
}