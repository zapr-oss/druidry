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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class JavaScriptAggregator extends DruidAggregator {

    private static final String JAVASCRIPT_AGGREGATOR_TYPE = "javascript";

    private List<String> fieldNames;

    @JsonProperty("fnAggregate")
    private String functionAggregate;

    @JsonProperty("fnCombine")
    private String functionCombine;

    @JsonProperty("fnReset")
    private String functionReset;

    @Builder
    private JavaScriptAggregator(@NonNull String name,
                                 @NonNull List<String> fieldNames,
                                 @NonNull String functionAggregate,
                                 @NonNull String functionCombine,
                                 @NonNull String functionReset) {

        this.type = JAVASCRIPT_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldNames = fieldNames;
        this.functionAggregate = functionAggregate;
        this.functionCombine = functionCombine;
        this.functionReset = functionReset;
    }
}