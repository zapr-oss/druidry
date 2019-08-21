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

package in.zapr.druid.druidry.filter;

import in.zapr.druid.druidry.filter.searchQuerySpec.SearchQuerySpec;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SearchFilter extends DruidFilter {

    private static String SEARCH_DRUID_FILTER_TYPE = "search";
    private String dimension;
    private SearchQuerySpec query;

    @Builder
    private SearchFilter(@NonNull String dimension, @NonNull SearchQuerySpec searchQuerySpec) {

        this.type = SEARCH_DRUID_FILTER_TYPE;
        this.dimension = dimension;
        this.query = searchQuerySpec;
    }

    // TODO: Extraction Function
}