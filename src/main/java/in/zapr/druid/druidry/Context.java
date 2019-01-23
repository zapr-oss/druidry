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

package in.zapr.druid.druidry;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Context {

    // TODO: Value validations. Like timeout > 0

    @JsonProperty("timeout")
    private Integer timeoutInMilliSeconds; // TODO: Should it be long??
    private Integer priority;
    private String queryId;
    private Boolean useCache;
    private Boolean populateCache;
    private Boolean bySegment;
    private Boolean finalize;

    // TODO: Allow only ISO-8601 period
    private String chunkPeriod = "0";
    private Integer minTopNThreshold;
    private Integer maxResults;
    private Integer maxIntermediateRows;
    private Boolean groupByIsSingleThreaded;

    private Boolean applyLimitPushDown;
    private Boolean forceLimitPushDown;


    public Integer getTimeoutInMilliSeconds() {
        return (timeoutInMilliSeconds != null && timeoutInMilliSeconds > 0) ?
                timeoutInMilliSeconds : null;
    }
}