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

package in.zapr.druid.druidry.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.zapr.druid.druidry.filter.SelectorFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilteredAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        SelectorFilter filter = new SelectorFilter("dimension", "value");
        CountAggregator countAggregator = new CountAggregator("Shakespear");

        FilteredAggregator filteredAggregator = new FilteredAggregator(filter, countAggregator);

        JSONObject filterObject = new JSONObject();
        filterObject.put("type", "selector");
        filterObject.put("dimension", "dimension");
        filterObject.put("value", "value");

        JSONObject aggregationObject = new JSONObject();
        aggregationObject.put("type", "count");
        aggregationObject.put("name", "Shakespear");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "filtered");
        jsonObject.put("filter", filterObject);
        jsonObject.put("aggregator", aggregationObject);

        String actualJSON = objectMapper.writeValueAsString(filteredAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }

    // TODO: Add testcase for equals
}