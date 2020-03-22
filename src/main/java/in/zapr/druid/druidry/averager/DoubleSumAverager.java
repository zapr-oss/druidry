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

package in.zapr.druid.druidry.averager;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleSumAverager extends DruidAverager {

    private static final String DOUBLE_SUM_AVERAGER = "doubleSum";

    @Builder
    private DoubleSumAverager(@NonNull String name, @NonNull String fieldName,
                              @NonNull Integer buckets, Integer cycleSize) {
        super(DOUBLE_SUM_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
