/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package in.zapr.druid.druidry.granularity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DurationGranularity extends Granularity {

    private static final String DURATION_GRANULARITY_TYPE = "duration";

    private final String type;
    @JsonProperty("duration")
    private long durationInMilliSeconds;
    private DateTime origin;

    public DurationGranularity(long durationInMilliSeconds) {
        this(durationInMilliSeconds, null);
    }

    public DurationGranularity(long durationInMilliSeconds, DateTime origin) {
        this.type = DURATION_GRANULARITY_TYPE;
        this.durationInMilliSeconds = durationInMilliSeconds;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin == null ? null : origin.toDateTimeISO().toString();
    }
}