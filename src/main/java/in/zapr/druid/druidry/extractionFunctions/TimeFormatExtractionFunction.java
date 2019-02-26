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

package in.zapr.druid.druidry.extractionFunctions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Locale;

import in.zapr.druid.druidry.granularity.Granularity;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeFormatExtractionFunction extends ExtractionFunction {

    private String format;

    private Locale locale;
    private Granularity granularity;
    private Boolean asMillis;

    //    TODO: search for a timezone library
    private String timeZone;

    @Builder
    private TimeFormatExtractionFunction(String format, Locale locale, Granularity granularity, String
            timeZone, Boolean asMillis) {
        this.type = TIME_FORMAT_TYPE;
        this.format = format;
        this.locale = locale;
        this.granularity = granularity;
        this.timeZone = timeZone;
        this.asMillis = asMillis;

    }

    public String getLocale() {
        return locale == null ? null : locale.toLanguageTag();
    }
}
