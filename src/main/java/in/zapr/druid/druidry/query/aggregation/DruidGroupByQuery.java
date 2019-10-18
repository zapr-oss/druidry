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

package in.zapr.druid.druidry.query.aggregation;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.having.DruidHaving;
import in.zapr.druid.druidry.limitSpec.DefaultLimitSpec;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import in.zapr.druid.druidry.query.QueryType;
import in.zapr.druid.druidry.virtualColumn.DruidVirtualColumn;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DruidGroupByQuery extends DruidAggregationQuery {

    private DefaultLimitSpec limitSpec;
    private DruidHaving having;
    @NonNull
    private List<DruidDimension> dimensions;

    @Builder
    private DruidGroupByQuery(@NonNull String dataSource,
                              @NonNull List<DruidDimension> dimensions,
                              DefaultLimitSpec limitSpec,
                              @NonNull Granularity granularity,
                              List<DruidVirtualColumn> virtualColumns,
                              DruidFilter filter,
                              List<DruidAggregator> aggregators,
                              List<DruidPostAggregator> postAggregators,
                              DruidHaving having,
                              @NonNull List<Interval> intervals,
                              Context context) {

        this.queryType = QueryType.GROUP_BY;
        this.dataSource = dataSource;
        this.dimensions = dimensions;
        this.limitSpec = limitSpec;
        this.granularity = granularity;
        this.virtualColumns = virtualColumns;
        this.filter = filter;
        this.aggregations = aggregators;
        this.postAggregations = postAggregators;
        this.having = having;
        this.intervals = intervals;
        this.context = context;
    }
}
