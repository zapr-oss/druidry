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

package in.zapr.druid.druidry.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class SelectorFilter extends DruidFilter {

    private static String SELECTOR_DRUID_FILTER_TYPE = "selector";

    private String dimension;
    private Object value;

    private SelectorFilter(@NonNull String dimension) {
        this.type = SELECTOR_DRUID_FILTER_TYPE;
        this.dimension = dimension;
    }

    public SelectorFilter(@NonNull String dimension, String value) {
        this(dimension);
        this.value = value;
    }

    public SelectorFilter(@NonNull String dimension, Integer value) {
        this(dimension);
        this.value = value;
    }

    public SelectorFilter(@NonNull String dimension, Long value) {
        this(dimension);
        this.value = value;
    }
}