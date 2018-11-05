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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public abstract class ExtractionFunction {
    protected static final String REGEX_TYPE = "regex";
    protected static final String PARTIAL_TYPE = "partial";
    protected static final String SEARCH_QUERY_TYPE = "searchQuery";
    protected static final String SUB_STRING_TYPE = "substring";
    protected static final String STRING_LENGTH_TYPE = "strlen";
    protected static final String TIME_FORMAT_TYPE = "timeFormat";
    protected static final String TIME_PARSING_TYPE = "time";
    protected static final String JAVASCRIPT_TYPE = "javascript";
    protected static final String LOOPUP_TYPE = "lookup";

    // todo: bottom 3 are left to code. Also check for timeZone in timeformat type. lookup is also left
    protected static final String REGISTERED_LOOKUP_TYPE = "registeredLookup";
    protected static final String CASCADE_TYPE = "cascade";
    protected static final String STRING_FORMAT_TYPE = "stringFormat";

    @NonNull
    protected String type;
}
