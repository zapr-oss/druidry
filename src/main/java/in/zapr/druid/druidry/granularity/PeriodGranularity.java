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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
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