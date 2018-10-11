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

package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ThetaSketchEstimatePostAggregator extends DruidPostAggregator {

    private static final String THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE = "thetaSketchEstimate";

    private DruidPostAggregator field;

    public ThetaSketchEstimatePostAggregator(@NonNull String name,
                                             @NonNull DruidPostAggregator field) {
        this.type = THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
