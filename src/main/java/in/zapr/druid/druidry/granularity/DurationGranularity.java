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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DurationGranularity extends Granularity {

    private static final String DURATION_GRANULARITY_TYPE = "duration";

    private final String type;
    @JsonProperty("duration")
    private long durationInMilliSeconds;
    private ZonedDateTime origin;

    public DurationGranularity(long durationInMilliSeconds) {
        this(durationInMilliSeconds, null);
    }

    public DurationGranularity(long durationInMilliSeconds, ZonedDateTime origin) {
        this.type = DURATION_GRANULARITY_TYPE;
        this.durationInMilliSeconds = durationInMilliSeconds;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin == null ? null : ISO_DATE_TIME.format(origin);
    }
}