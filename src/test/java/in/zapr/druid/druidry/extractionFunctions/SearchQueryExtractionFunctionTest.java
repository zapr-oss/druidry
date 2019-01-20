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

package in.zapr.druid.druidry.extractionFunctions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.zapr.druid.druidry.filter.searchQuerySpec.RegexSearchQuerySpec;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchQueryExtractionFunctionTest {
    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        RegexSearchQuerySpec regexSearchQuerySpec = RegexSearchQuerySpec.builder()
                .pattern("some_pattern")
                .build();

        SearchQueryExtractionFunction searchQueryextractionFunction = SearchQueryExtractionFunction.builder()
                .query(regexSearchQuerySpec)
                .build();

        String actualJSON = objectMapper.writeValueAsString(searchQueryextractionFunction);

        String expectedJSONString = "{ \"type\" : \"searchQuery\", \"query\" : {\n  \"type\"  : \"regex\",\n  \"pattern\" : \"some_pattern\"\n} }\n\n";

        JSONAssert.assertEquals(expectedJSONString, actualJSON, JSONCompareMode.NON_EXTENSIBLE);

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSearchQueryField() {
        SearchQueryExtractionFunction searchQueryextractionFunction = SearchQueryExtractionFunction.builder().build();
    }
}
