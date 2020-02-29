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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@Getter
@EqualsAndHashCode
public class Interval {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private final static String DRUID_INTERVAL_FORMAT = "%s/%s";

    private TemporalAccessor startTime;
    private TemporalAccessor endTime;

    public Interval(@NonNull TemporalAccessor startTime, @NonNull TemporalAccessor endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @JsonValue
    private String getIntervalAsString() {
        return String.format(DRUID_INTERVAL_FORMAT, FORMATTER.format(startTime), FORMATTER.format(endTime));
    }
}