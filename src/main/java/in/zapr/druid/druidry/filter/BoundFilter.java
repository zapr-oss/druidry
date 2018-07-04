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

import in.zapr.druid.druidry.SortingOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BoundFilter extends DruidFilter {

    private static String BOUND_DRUID_FILTER_TYPE = "bound";

    @NonNull
    private String dimension;
    private String lower;
    private String upper;
    private Boolean lowerStrict;
    private Boolean upperStrict;
    private SortingOrder ordering;

    // TODO: support for Extraction Function

    @Builder
    private BoundFilter(@NonNull String dimension,
                        String lower,
                        String upper,
                        Boolean lowerStrict,
                        Boolean upperStrict,
                        SortingOrder ordering) {

        this.type = BOUND_DRUID_FILTER_TYPE;
        this.dimension = dimension;
        this.lower = lower;
        this.upper = upper;
        this.lowerStrict = lowerStrict;
        this.upperStrict = upperStrict;
        this.ordering = ordering;
    }
}
