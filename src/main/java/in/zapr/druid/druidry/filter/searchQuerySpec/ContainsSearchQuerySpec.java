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

package in.zapr.druid.druidry.filter.searchQuerySpec;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ContainsSearchQuerySpec extends SearchQuerySpec {

    private final static String CONTAINS_SEARCH_QUERY_SPEC = "contains";

    private String value;
    private Boolean caseSensitive;

    public ContainsSearchQuerySpec(@NonNull String value) {
        this(value, null);
    }

    public ContainsSearchQuerySpec(@NonNull String value, Boolean isCaseSensitive) {
        this.type = CONTAINS_SEARCH_QUERY_SPEC;
        this.value = value;
        this.caseSensitive = isCaseSensitive;
    }
}