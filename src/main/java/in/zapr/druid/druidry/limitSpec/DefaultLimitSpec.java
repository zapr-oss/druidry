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

package in.zapr.druid.druidry.limitSpec;

import java.util.List;

import in.zapr.druid.druidry.limitSpec.orderByColumnSpec.OrderByColumnSpec;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class DefaultLimitSpec {
    private static final String DEFAULT_LIMIT_SPEC_TYPE = "default";

    private String type;
    private int limit;
    private int offset;
    private List<OrderByColumnSpec> columns;

    public DefaultLimitSpec(int limit, @NonNull List<OrderByColumnSpec> columns) {
        this(limit, 0, columns);
    }

    public DefaultLimitSpec(int limit, int offset, @NonNull List<OrderByColumnSpec> columns) {
        this.type = DEFAULT_LIMIT_SPEC_TYPE;
        this.limit = limit;
        this.offset = offset;
        this.columns = columns;
    }
}