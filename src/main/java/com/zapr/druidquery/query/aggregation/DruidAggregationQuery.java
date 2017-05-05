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

package com.zapr.druidquery.query.aggregation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zapr.druidquery.Interval;
import com.zapr.druidquery.aggregator.DruidAggregator;
import com.zapr.druidquery.filter.DruidFilter;
import com.zapr.druidquery.granularity.Granularity;
import com.zapr.druidquery.postAggregator.DruidPostAggregator;
import com.zapr.druidquery.query.DruidQuery;

import java.util.List;

import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public abstract class DruidAggregationQuery extends DruidQuery {
    protected List<Interval> intervals;
    protected Granularity granularity;
    protected DruidFilter filter;
    protected List<DruidAggregator> aggregations;
    protected List<DruidPostAggregator> postAggregations;
}
