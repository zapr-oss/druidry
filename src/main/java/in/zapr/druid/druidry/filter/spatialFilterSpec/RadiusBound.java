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

package in.zapr.druid.druidry.filter.spatialFilterSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RadiusBound extends SpatialFilterBoundSpec {
    private static String SPATIAL_BOUND_TYPE = "radius";

    private List<? extends Number> coords;
    private Number radius;

    public RadiusBound(@NonNull List<? extends Number> coords, @NonNull Number radius) {
        this.type = SPATIAL_BOUND_TYPE;
        this.coords = coords;
        this.radius = radius;
    }

}
