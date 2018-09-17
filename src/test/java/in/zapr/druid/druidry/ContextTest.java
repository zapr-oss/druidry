package in.zapr.druid.druidry;/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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