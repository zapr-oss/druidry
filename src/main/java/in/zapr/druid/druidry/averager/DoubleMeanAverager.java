package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleMeanAverager extends DruidAverager {

    private static final String DOUBLE_MEAN_AVERAGER = "doubleMean";

    public DoubleMeanAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public DoubleMeanAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(DOUBLE_MEAN_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
