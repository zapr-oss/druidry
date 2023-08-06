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

package in.zapr.druid.druidry.query.config;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortingOrder {
    LEXICOGRAPHIC("lexicographic"),
    ALPHANUMERIC("alphanumeric"),
    NUMERIC("numeric"),
    STRLEN("strlen"),
    VERSION("version");

    private String value;

    SortingOrder(String value) {
        this.value = value;
    }

    @JsonValue
    public String getSortingOrder() {
        return value;
    }
}