package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleSumAverager extends DruidAverager {

    private static final String DOUBLE_SUM_AVERAGER = "doubleSum";

    public DoubleSumAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public DoubleSumAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(DOUBLE_SUM_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
