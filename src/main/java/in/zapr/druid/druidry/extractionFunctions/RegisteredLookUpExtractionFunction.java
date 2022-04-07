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

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisteredLookUpExtractionFunction extends ExtractionFunction {

    private String lookup;
    private Boolean retainMissingValue;
    private Boolean injective;
    private String replaceMissingValueWith;
    private Boolean optimize;

    @Builder
    private RegisteredLookUpExtractionFunction(String lookup, Boolean retainMissingValue, Boolean injective,
                                               String replaceMissingValueWith, Boolean optimize) {
        this.type = ExtractionFunction.REGISTERED_LOOKUP_TYPE;
        this.lookup = lookup;
        this.retainMissingValue = retainMissingValue;
        this.injective = injective;
        this.replaceMissingValueWith = replaceMissingValueWith;
        this.optimize = optimize;
    }
}
