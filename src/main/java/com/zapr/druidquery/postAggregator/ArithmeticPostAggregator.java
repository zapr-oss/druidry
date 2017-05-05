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

package com.zapr.druidquery.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArithmeticPostAggregator extends DruidPostAggregator {

    private final static String ARITHMETIC_POST_AGGREGATOR_TYPE = "arithmetic";

    @JsonProperty("fn")
    private ArithmeticFunction function;
    private List<DruidPostAggregator> fields;
    private ArithmeticOrdering ordering;

    @Builder
    private ArithmeticPostAggregator(@NonNull String name,
                                     @NonNull ArithmeticFunction function,
                                     @NonNull List<DruidPostAggregator> fields,
                                     ArithmeticOrdering ordering) {
        this.type = ARITHMETIC_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.function = function;
        this.fields = fields;
        this.ordering = ordering;
    }
}