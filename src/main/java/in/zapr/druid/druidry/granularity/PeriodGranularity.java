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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class PeriodGranularity extends Granularity {
    private static final String PERIOD_GRANULARITY_TYPE = "period";
    private static final String DEFAULT_TIMEZONE = "UTC";

    private final String type = PERIOD_GRANULARITY_TYPE;

    // TODO: ISO-8601 Period validation, Check it is alright
    // TODO: while building make sure that it is required
    private String period;

    // TODO: Check if it is alright
    private DateTimeZone timeZone;
    private DateTime origin;

    public String getOrigin() {
        return origin == null ? null : origin.toDateTimeISO().toString();
    }

    public String getTimeZone() {
        return timeZone == null ? DEFAULT_TIMEZONE : timeZone.getID();
    }
}