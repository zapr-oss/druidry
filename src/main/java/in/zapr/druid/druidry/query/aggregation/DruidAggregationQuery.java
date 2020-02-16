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

import in.zapr.druid.druidry.query.config.Interval;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import in.zapr.druid.druidry.query.DruidQuery;
import in.zapr.druid.druidry.virtualColumn.DruidVirtualColumn;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class DruidAggregationQuery extends DruidQuery {
    protected List<Interval> intervals;
    protected Granularity granularity;
    protected List<DruidVirtualColumn> virtualColumns;
    protected DruidFilter filter;
    protected List<DruidAggregator> aggregations;
    protected List<DruidPostAggregator> postAggregations;
}
