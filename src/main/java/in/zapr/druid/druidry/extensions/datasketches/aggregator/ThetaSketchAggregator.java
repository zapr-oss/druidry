/*
 * Copyright 2018-present Red Brick Lane Marketing Solutions Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.zapr.druid.druidry.extensions.datasketches.aggregator;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.aggregator.DruidAggregator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
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
