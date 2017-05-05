/*
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

package aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zapr.druidquery.aggregator.CountAggregator;
import com.zapr.druidquery.aggregator.FilteredAggregator;
import com.zapr.druidquery.filter.SelectorFilter;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
}