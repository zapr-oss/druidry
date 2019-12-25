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

package in.zapr.druid.druidry.filter;

import java.util.List;

import in.zapr.druid.druidry.query.config.Interval;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class IntervalFilter extends DruidFilter {

    private static String INTERVAL_DRUID_FILTER_TYPE = "interval";

    private String type;
    private String dimension;
    private List<Interval> intervals;

    public IntervalFilter(@NonNull String dimension, @NonNull List<Interval> intervals) {
        this.type = INTERVAL_DRUID_FILTER_TYPE;
        this.dimension = dimension;
        this.intervals = intervals;
    }

    // TODO: support for Extraction Function
}
