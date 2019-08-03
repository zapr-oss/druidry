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

import java.util.List;

import in.zapr.druid.druidry.aggregator.DruidAggregator;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class TupleSketchAggregator extends DruidAggregator {

    private static final String TUPLE_SKETCH_TYPE_AGGREGATOR = "arrayOfDoublesSketch";
    private String fieldName;
    private Integer nominalEntries;
    private Integer numberOfValues;
    private List<String> metricColumns;

    @Builder
    private TupleSketchAggregator(@NonNull String name,
                                  @NonNull String fieldName,
                                  Integer nominalEntries,
                                  Integer numberOfValues,
                                  List<String> metricColumns) {
        this.type = TUPLE_SKETCH_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
        this.nominalEntries = nominalEntries;
        this.numberOfValues = numberOfValues;
        this.metricColumns = metricColumns;

        if (nominalEntries != null) {
            Preconditions.checkArgument(LongMath.isPowerOfTwo(nominalEntries), "nominalEntries must be a power of 2");
        }
    }

}
