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

package com.zapr.druidquery.aggregator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class JavaScriptAggregator extends DruidAggregator {

    private static final String JAVASCRIPT_AGGREGATOR_TYPE = "javascript";

    private List<String> fieldNames;

    @JsonProperty("fnAggregate")
    private String functionAggregate;

    @JsonProperty("fnCombine")
    private String functionCombine;

    @JsonProperty("fnReset")
    private String functionReset;

    @Builder
    private JavaScriptAggregator(@NonNull String name,
                                 @NonNull List<String> fieldNames,
                                 @NonNull String functionAggregate,
                                 @NonNull String functionCombine,
                                 @NonNull String functionReset) {

        this.type = JAVASCRIPT_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldNames = fieldNames;
        this.functionAggregate = functionAggregate;
        this.functionCombine = functionCombine;
        this.functionReset = functionReset;
    }
}