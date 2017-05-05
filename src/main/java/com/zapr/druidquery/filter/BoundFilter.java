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

import com.zapr.druidquery.SortingOrder;

import lombok.Getter;

@Getter
public class BoundFilter extends DruidFilter {

    private static String BOUND_DRUID_FILTER_TYPE = "bound";

    private String dimension;
    private String lower;
    private String upper;
    private Boolean lowerStrict;
    private Boolean upperStrict;
    private SortingOrder ordering;

    // TODO: support for Extraction Function
}
