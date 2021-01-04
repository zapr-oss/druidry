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
package in.zapr.druid.druidry.dataSource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class JoinDataSource extends DataSource{

    @NonNull
    private DataSource left, right;

    private String rightPrefix="r.";
    private String condition;
    private JoinType joinType= JoinType.INNER;

    @Builder
    public JoinDataSource(@NonNull DataSource left,  DataSource right, @NonNull String condition,
           String rightPrefix, JoinType joinType) {
        this.type = DataSourceType.JOIN;
        this.left=left;
        this.right=right;
        this.condition=condition;
        if(joinType!=null) {
            this.joinType = joinType;
        }
        if(StringUtils.isNotBlank(rightPrefix))
        {
            this.rightPrefix=rightPrefix;
        }
    }

}