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

package in.zapr.druid.druidry.limitSpec.orderByColumnSpec;

import in.zapr.druid.druidry.SortingOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OrderByColumnSpecMap extends OrderByColumnSpec {

    private static final String ASCENDING_ORDER = "ascending";
    private static final String DESCENDING_ORDER = "descending";

    private String dimension;
    private String direction;
    private SortingOrder dimensionOrder;

    public OrderByColumnSpecMap(String dimension, boolean isAscending, SortingOrder dimensionOrder) {
        this.dimension = dimension;
        this.direction = isAscending ? ASCENDING_ORDER : DESCENDING_ORDER;
        this.dimensionOrder = dimensionOrder;
    }
}
