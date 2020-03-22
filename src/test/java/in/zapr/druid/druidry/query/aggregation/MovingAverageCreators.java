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

package in.zapr.druid.druidry.query.aggregation;

import in.zapr.druid.druidry.averager.DoubleMaxAverager;
import in.zapr.druid.druidry.averager.DoubleMeanAverager;
import in.zapr.druid.druidry.averager.DoubleMeanNoNullsAverager;
import in.zapr.druid.druidry.averager.DoubleMinAverager;
import in.zapr.druid.druidry.averager.DoubleSumAverager;
import in.zapr.druid.druidry.averager.DruidAverager;
import in.zapr.druid.druidry.averager.LongMaxAverager;
import in.zapr.druid.druidry.averager.LongMeanAverager;
import in.zapr.druid.druidry.averager.LongMeanNoNullsAverager;
import in.zapr.druid.druidry.averager.LongMinAverager;
import in.zapr.druid.druidry.averager.LongSumAverager;

class MovingAverageCreators {

    private MovingAverageCreators() {

    }

    static DruidAverager doubleMax(String name, String fieldName, int buckets) {
        return DoubleMaxAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager doubleMean(String name, String fieldName, int buckets) {
        return DoubleMeanAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager doubleMeanNoNulls(String name, String fieldName, int buckets) {
        return DoubleMeanNoNullsAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager doubleMin(String name, String fieldName, int buckets) {
        return DoubleMinAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager doubleSum(String name, String fieldName, int buckets) {
        return DoubleSumAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager longMax(String name, String fieldName, int buckets) {
        return LongMaxAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager longMean(String name, String fieldName, int buckets) {
        return LongMeanAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager longMeanNoNulls(String name, String fieldName, int buckets) {
        return LongMeanNoNullsAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager longMin(String name, String fieldName, int buckets) {
        return LongMinAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    public static DruidAverager longSum(String name, String fieldName, int buckets) {
        return LongSumAverager.builder()
                .name(name)
                .fieldName(fieldName)
                .buckets(buckets)
                .build();
    }

    @FunctionalInterface
    interface Creator {

        DruidAverager apply(String name, String fieldName, int buckets);

    }

}
