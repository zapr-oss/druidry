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
package in.zapr.druid.druidry.query.select;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.dataSource.DataSource;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.query.DruidQuery;
import in.zapr.druid.druidry.query.QueryType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

/**
 * Generate Druid select query. See documentation http://druid.io/docs/latest/querying/select-query.html
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DruidSelectQuery extends DruidQuery {
    private List<Interval> intervals;
    private DruidFilter filter;
    private Boolean descending;
    private Granularity granularity;
    private List<DruidDimension> dimensions;
    private List<String> metrics;
    private PagingSpec pagingSpec;

    @Builder
    public DruidSelectQuery(
            @NonNull DataSource dataSource,
            DruidFilter filter,
            Boolean descending,
            Granularity granularity,
            List<DruidDimension> dimensions,
            List<String> metrics,
            @NonNull List<Interval> intervals,
            @NonNull PagingSpec pagingSpec,
            Context context) {
        this.queryType = QueryType.SELECT;
        this.context = context;
        this.dataSource = dataSource;
        this.filter = filter;
        this.descending = descending;
        this.granularity = granularity;
        this.intervals = intervals;
        this.dimensions = dimensions;
        this.metrics = metrics;
        this.pagingSpec = pagingSpec;
    }
}
