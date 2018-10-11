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

package in.zapr.druid.druidry.query.scan;

import com.google.common.base.Preconditions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.dimension.DruidDimension;
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
    private List<DruidDimension> columns;
    private String resultFormat;
    private Long limit;
    private Boolean legacy;

    @Builder
    private DruidScanQuery(@NonNull String dataSource, DruidFilter filter, Integer batchSize, @NonNull List<Interval> intervals, List<DruidDimension> columns, String resultFormat, Long limit, Boolean legacy, Context context) {
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
