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

package in.zapr.druid.druidry.aggregator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Builder;



@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VarianceAggregator extends DruidAggregator {
    private static final String VARIANCE_TYPE_AGGREGATOR = "variance";

    private final String fieldName;
    private final String expression;

    // One of "float", "double", "long", "variance"; defaults to "float".
    private final String inputType;

    // Use "population" to get variance_pop rather than variance_sample, which is default.
    private final String estimator;

    @Builder
    private VarianceAggregator(@NonNull String name, String fieldName, String expression, String inputType, String estimator) {
        this.type = VARIANCE_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
        this.expression = expression;
        this.inputType = inputType;
        this.estimator = estimator;

        Preconditions.checkArgument(
                fieldName == null ^ expression == null,
                "Must have a valid, non-null fieldName or expression"
        );
    }
}
