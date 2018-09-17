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

import in.zapr.druid.druidry.Interval;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class IntervalFilter extends DruidFilter {

    private static String INTERVAL_DRUID_FILTER_TYPE = "interval";

    private String type;
    private String dimension;
    private List<Interval> intervals;

    public IntervalFilter(@NonNull String dimension, @NonNull List<Interval> intervals) {
        this.type = INTERVAL_DRUID_FILTER_TYPE;
        this.dimension = dimension;
        this.intervals = intervals;
    }

    // TODO: support for Extraction Function
}
