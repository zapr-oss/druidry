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

package in.zapr.druid.druidry.filter.searchQuerySpec;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class FragmentSearchQuerySpec extends SearchQuerySpec {

    private final static String FRAGMENT_SEARCH_QUERY_SPEC = "fragment";

    private List<String> values;
    private Boolean caseSensitive;

    public FragmentSearchQuerySpec(@NonNull List<String> values) {
        this(values, null);
    }

    public FragmentSearchQuerySpec(@NonNull List<String> values, Boolean isCaseSensitive) {
        this.type = FRAGMENT_SEARCH_QUERY_SPEC;
        this.values = values;
        this.caseSensitive = isCaseSensitive;
    }
}