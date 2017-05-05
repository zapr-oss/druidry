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

package com.zapr.druidquery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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


    public Integer getTimeoutInMilliSeconds() {
        return (timeoutInMilliSeconds != null && timeoutInMilliSeconds > 0) ?
                timeoutInMilliSeconds : null;
    }
}