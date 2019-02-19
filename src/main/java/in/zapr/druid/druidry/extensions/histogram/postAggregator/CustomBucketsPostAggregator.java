/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */

package in.zapr.druid.druidry.extensions.histogram.postAggregator;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomBucketsPostAggregator extends DruidPostAggregator {

  private static final String CUSTOMBUCKET_POST_AGGREGATOR_TYPE = "customBuckets";
  private String name;
  private String fieldName;
  private Set<Float> breaks;

  @Builder
  private CustomBucketsPostAggregator(@NonNull String name, @NonNull String fieldName,
      @NonNull Set<Float> breaks) {
    this.type = CUSTOMBUCKET_POST_AGGREGATOR_TYPE;
    this.name = name;
    this.fieldName = fieldName;
    this.breaks = breaks;
  }
}
