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

package in.zapr.druid.druidry.aggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApproxHistogramFoldAggregator extends DruidAggregator {

    private static final String APPROX_HISTOGRAM_AGGREGATOR_TYPE = "approxHistogramFold";
    private String name;
    private String fieldName;
    private Integer resolution;
    private Float lowerLimit = Float.NEGATIVE_INFINITY;
    private Float upperLimit = Float.POSITIVE_INFINITY;
    private Integer numberOfBuckets;

    @Builder
    private ApproxHistogramFoldAggregator(@NonNull String name, @NonNull String fieldName, Integer resolution,
    		Float lowerLimit, Float upperLimit, Integer numberOfBuckets) {
        this.type = APPROX_HISTOGRAM_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.resolution = resolution;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.numberOfBuckets = numberOfBuckets;
    }

}
