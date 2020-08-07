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

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode(callSuper = true)
public class CardinalityAggregator extends DruidAggregator {

    private static final String CARDINALITY_AGGREGATOR_TYPE = "cardinality";
    private List<String> fields;
    private Boolean byRow;
    private Boolean round;

    @Builder
    private CardinalityAggregator(@NonNull String name, @NonNull List<String> fields, Boolean byRow, Boolean round) {
        this.type = CARDINALITY_AGGREGATOR_TYPE;
        this.name = name;
        this.fields = fields;
        this.byRow = byRow;
        this.round = round;
    }
}