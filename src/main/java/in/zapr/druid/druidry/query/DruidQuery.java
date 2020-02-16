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

package in.zapr.druid.druidry.query;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.query.config.Context;
import in.zapr.druid.druidry.dataSource.DataSource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public abstract class DruidQuery {

    @NonNull
    protected DataSource dataSource;

    protected Context context;

    // Not making it public since this should be set by its children's constructor.
    @NonNull
    protected QueryType queryType;
}