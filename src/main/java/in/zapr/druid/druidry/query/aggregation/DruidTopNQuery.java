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

package in.zapr.druid.druidry.query.aggregation;

import com.google.common.base.Preconditions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import in.zapr.druid.druidry.query.QueryType;
import in.zapr.druid.druidry.topNMetric.TopNMetric;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DruidTopNQuery extends DruidAggregationQuery {

    private DruidDimension dimension;
    private Integer threshold;
    private TopNMetric metric;

    @Builder
    private DruidTopNQuery(@NonNull String dataSource,
                           @NonNull List<Interval> intervals,
                           @NonNull Granularity granularity,
                           DruidFilter filter,
                           List<DruidAggregator> aggregators,
                           List<DruidPostAggregator> postAggregators,
                           @NonNull DruidDimension dimension,
                           @NonNull Integer threshold,
                           @NonNull TopNMetric topNMetric,
                           Context context) {

        this.queryType = QueryType.TOPN;
        this.dataSource = dataSource;
        this.intervals = intervals;
        this.granularity = granularity;
        this.filter = filter;
        this.aggregations = aggregators;
        this.postAggregations = postAggregators;
        this.dimension = dimension;
        this.threshold = threshold;
        this.metric = topNMetric;
        this.context = context;

        Preconditions.checkArgument(threshold > 0, "threshold must be greater than 0");
    }
}
