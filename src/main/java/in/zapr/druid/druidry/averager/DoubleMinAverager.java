package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleMinAverager extends DruidAverager {

    private static final String DOUBLE_MIN_AVERAGER = "doubleMin";

    public DoubleMinAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public DoubleMinAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(DOUBLE_MIN_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
