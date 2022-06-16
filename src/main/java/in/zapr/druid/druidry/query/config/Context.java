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

package in.zapr.druid.druidry.query.config;

import com.google.common.base.Preconditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class Context {

    // general query contexts
    @JsonProperty("timeout")
    private Long timeoutInMilliSeconds;
    private Integer priority;
    private String queryId;
    private Boolean useCache;
    private Boolean populateCache;
    private Boolean useResultLevelCache;
    private Boolean populateResultLevelCache;
    private Boolean bySegment;
    private Boolean finalize;
    private String chunkPeriod;
    private Long maxScatterGatherBytes;
    private Long maxQueuedBytes;
    private Boolean serializeDateTimeAsLong;
    private Boolean serializeDateTimeAsLongInner;

    // topN query contexts
    private Integer minTopNThreshold;

    // timeseries query contexts
    private Boolean skipEmptyBuckets;
    private Boolean grandTotal;

    // groupBy query contexts
    private Long maxMergingDictionarySize;
    private Long maxOnDiskStorage;
    private GroupByStrategy groupByStrategy;
    private Boolean groupByIsSingleThreaded;
    private Integer bufferGrouperInitialBuckets;
    private Float bufferGrouperMaxLoadFactor;
    private Boolean forceHashAggregation;
    private Integer intermediateCombineDegree;
    private Integer numParallelCombineThreads;
    private Boolean sortByDimsFirst;
    private Boolean forceLimitPushDown;
    private Boolean applyLimitPushDown;
    private Integer maxIntermediateRows;
    private Integer maxResults;
    private Boolean useOffheap;

    // groupBy && timeseries query experimental contexts
    private Vectorize vectorize;
    private Integer vectorSize;

    private Context(Long timeoutInMilliSeconds,
                    Integer priority,
                    String queryId,
                    Boolean useCache,
                    Boolean populateCache,
                    Boolean useResultLevelCache,
                    Boolean populateResultLevelCache,
                    Boolean bySegment,
                    Boolean finalize,
                    String chunkPeriod,
                    Long maxScatterGatherBytes,
                    Long maxQueuedBytes,
                    Boolean serializeDateTimeAsLong,
                    Boolean serializeDateTimeAsLongInner,
                    Integer minTopNThreshold,
                    Boolean skipEmptyBuckets,
                    Boolean grandTotal,
                    Long maxMergingDictionarySize,
                    Long maxOnDiskStorage,
                    GroupByStrategy groupByStrategy,
                    Boolean groupByIsSingleThreaded,
                    Integer bufferGrouperInitialBuckets,
                    Float bufferGrouperMaxLoadFactor,
                    Boolean forceHashAggregation,
                    Integer intermediateCombineDegree,
                    Integer numParallelCombineThreads,
                    Boolean sortByDimsFirst,
                    Boolean forceLimitPushDown,
                    Boolean applyLimitPushDown,
                    Integer maxIntermediateRows,
                    Integer maxResults,
                    Boolean useOffheap,
                    Vectorize vectorize,
                    Integer vectorSize) {

        this.timeoutInMilliSeconds = timeoutInMilliSeconds;
        this.priority = priority;
        this.queryId = queryId;
        this.useCache = useCache;
        this.populateCache = populateCache;
        this.useResultLevelCache = useResultLevelCache;
        this.populateResultLevelCache = populateResultLevelCache;
        this.bySegment = bySegment;
        this.finalize = finalize;
        this.chunkPeriod = chunkPeriod;
        this.maxScatterGatherBytes = maxScatterGatherBytes;
        this.maxQueuedBytes = maxQueuedBytes;
        this.serializeDateTimeAsLong = serializeDateTimeAsLong;
        this.serializeDateTimeAsLongInner = serializeDateTimeAsLongInner;
        this.minTopNThreshold = minTopNThreshold;
        this.skipEmptyBuckets = skipEmptyBuckets;
        this.grandTotal = grandTotal;
        this.maxMergingDictionarySize = maxMergingDictionarySize;
        this.maxOnDiskStorage = maxOnDiskStorage;
        this.groupByStrategy = groupByStrategy;
        this.groupByIsSingleThreaded = groupByIsSingleThreaded;
        this.bufferGrouperInitialBuckets = bufferGrouperInitialBuckets;
        this.bufferGrouperMaxLoadFactor = bufferGrouperMaxLoadFactor;
        this.forceHashAggregation = forceHashAggregation;
        this.intermediateCombineDegree = intermediateCombineDegree;
        this.numParallelCombineThreads = numParallelCombineThreads;
        this.sortByDimsFirst = sortByDimsFirst;
        this.forceLimitPushDown = forceLimitPushDown;
        this.applyLimitPushDown = applyLimitPushDown;
        this.maxIntermediateRows = maxIntermediateRows;
        this.maxResults = maxResults;
        this.useOffheap = useOffheap;
        this.vectorize = vectorize;
        this.vectorSize = vectorSize;

        if (timeoutInMilliSeconds != null) {
            Preconditions.checkArgument(timeoutInMilliSeconds >= 0, "Timeout must be a non negative value, but was [%s]", timeoutInMilliSeconds);
        }
    }
}