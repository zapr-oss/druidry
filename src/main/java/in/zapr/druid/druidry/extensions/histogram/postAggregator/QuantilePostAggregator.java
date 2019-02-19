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

package in.zapr.druid.druidry.extensions.histogram.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilePostAggregator extends DruidPostAggregator {

    private static final String QUANTILE_POST_AGGREGATOR_TYPE = "quantile";
    private String fieldName;
    private Float probability;

    @Builder
    private QuantilePostAggregator(@NonNull String name, @NonNull String fieldName,
                                   @NonNull Float probability) {
        this.type = QUANTILE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.probability = probability;
    }
}
