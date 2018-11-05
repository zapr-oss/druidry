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

package in.zapr.druid.druidry.granularity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PredefinedGranularity {

    NONE("none"),
    ALL("all"),
    SECOND("second"),
    MINUTE("minute"),
    FIFTEEN_MINUTE("fifteen_minute"),
    THIRTY_MINUTE("thirty_minute"),
    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month"),
    QUARTER("quarter"),
    YEAR("year");

    private String value;

    PredefinedGranularity(String value) {
        this.value = value;
    }

    @JsonValue
    public String getPredefinedGranularityString() {
        return value;
    }
}