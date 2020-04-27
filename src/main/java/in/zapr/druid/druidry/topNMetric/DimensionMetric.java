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

package in.zapr.druid.druidry.topNMetric;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.query.config.SortingOrder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DimensionMetric extends TopNMetric {

    private static final String DIMENSION_METRIC_TYPE = "dimension";

    private String type;
    private SortingOrder ordering;
    private String previousStop;

    @Builder
    private DimensionMetric(SortingOrder ordering, String previousStop) {
        this.type = DIMENSION_METRIC_TYPE;
        this.ordering = ordering;
        this.previousStop = previousStop;
    }

}
