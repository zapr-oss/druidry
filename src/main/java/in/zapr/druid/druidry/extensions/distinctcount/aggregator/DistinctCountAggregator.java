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

package in.zapr.druid.druidry.extensions.distinctcount.aggregator;

import in.zapr.druid.druidry.aggregator.DruidAggregator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DistinctCountAggregator extends DruidAggregator {

    private static final String DISTINCT_COUNT_AGGREGATOR_TYPE = "distinctCount";
    private String fieldName;

    public DistinctCountAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = DISTINCT_COUNT_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
    }

}
