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

package com.zapr.druidquery.filter;

import com.zapr.druidquery.filter.searchQuerySpec.SearchQuerySpec;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
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