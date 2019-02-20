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

package in.zapr.druid.druidry.query.search;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.SortingOrder;
import in.zapr.druid.druidry.dataSource.DataSource;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.filter.searchQuerySpec.SearchQuerySpec;
import in.zapr.druid.druidry.granularity.Granularity;
import in.zapr.druid.druidry.query.DruidQuery;
import in.zapr.druid.druidry.query.QueryType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DruidSearchQuery extends DruidQuery {

    private Granularity granularity;
    private DruidFilter filter;
    private Integer limit;
    private List<Interval> intervals;
    private List<DruidDimension> searchDimensions;
    private SearchQuerySpec query;
    private SearchSortSpec sort;

    @Builder
    private DruidSearchQuery(@NonNull DataSource dataSource,
                             @NonNull Granularity granularity,
                             DruidFilter filter,
                             Integer limit,
                             @NonNull List<Interval> intervals,
                             List<DruidDimension> searchDimensions,
                             @NonNull SearchQuerySpec query,
                             SortingOrder sort,
                             Context context) {

        this.queryType = QueryType.SEARCH;
        this.dataSource = dataSource;
        this.granularity = granularity;
        this.filter = filter;
        this.limit = limit;
        this.intervals = intervals;
        this.searchDimensions = searchDimensions;
        this.query = query;
        if (sort != null) {
            this.sort = new SearchSortSpec(sort);
        }
        this.context = context;
    }
}
