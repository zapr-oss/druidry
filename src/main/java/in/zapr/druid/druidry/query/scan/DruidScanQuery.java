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

package in.zapr.druid.druidry.query.scan;

import com.google.common.base.Preconditions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.dataSource.DataSource;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.query.DruidQuery;
import in.zapr.druid.druidry.query.QueryType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DruidScanQuery extends DruidQuery {

    private DruidFilter filter;
    private Integer batchSize;
    private List<Interval> intervals;
    private List<String> columns;
    private ResultFormat resultFormat;
    private Long limit;
    private Boolean legacy;

    @Builder
    private DruidScanQuery(@NonNull DataSource dataSource, DruidFilter filter, Integer batchSize, @NonNull List<Interval> intervals, List<String> columns, ResultFormat resultFormat, Long limit, Boolean legacy, Context context) {
        this.filter = filter;
        this.intervals = intervals;
        this.columns = columns;
        this.resultFormat = resultFormat;
        this.queryType = QueryType.SCAN;
        this.context = context;
        this.dataSource = dataSource;
        this.legacy = legacy;
        this.limit = limit;
        this.batchSize = batchSize;
        if (limit != null) {
            Preconditions.checkArgument(limit > 0, "limit specified must be more than 0");
        }
        if (batchSize != null) {
            Preconditions.checkArgument(batchSize > 0, "batchSize specified must be more than 0");
        }
    }
}
