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

package in.zapr.druid.druidry.extensions.datasketches.aggregator;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.aggregator.DruidAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThetaSketchAggregator extends DruidAggregator {

    private static final String THETA_SKETCH_TYPE_AGGREGATOR = "thetaSketch";
    private String fieldName;
    private Boolean isInputThetaSketch;
    private Long size;

    @Builder
    public ThetaSketchAggregator(
            @NonNull String name,
            @NonNull String fieldName,
            Boolean isInputThetaSketch,
            Long size) {
        this.type = THETA_SKETCH_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
        this.isInputThetaSketch = isInputThetaSketch;
        this.size = size;

        if (size != null) {
            Preconditions.checkArgument(LongMath.isPowerOfTwo(size), "size must be a power of 2");
        }
    }

}
