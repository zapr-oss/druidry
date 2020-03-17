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

import com.google.common.base.Preconditions;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LongSumAggregator extends DruidAggregator {

    private static final String LONGSUM_TYPE_AGGREGATOR = "longSum";

    private String fieldName;
    private String expression;

    @Deprecated
    public LongSumAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = LONGSUM_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }

    @Builder
    private LongSumAggregator(@NonNull String name, String fieldName, String expression) {
        this.type = LONGSUM_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
        this.expression = expression;

        Preconditions.checkArgument(
            fieldName == null ^ expression == null,
            "Must have a valid, non-null fieldName or expression"
        );
    }

}
