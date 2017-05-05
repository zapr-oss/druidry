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

import lombok.Getter;
import lombok.NonNull;

@Getter
public class HyperUniqueAggregator extends DruidAggregator {

    private static final String HYPER_UNIQUE_TYPE_AGGREGATOR = "hyperUnique";
    private String fieldName;

    public HyperUniqueAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = HYPER_UNIQUE_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}