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

package com.zapr.druidquery.extractionFunctions;

import lombok.Getter;
import lombok.NonNull;

@Getter
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
