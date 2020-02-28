package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleMaxAverager extends DruidAverager {

    private static final String DOUBLE_MAX_AVERAGER = "doubleMax";

    public DoubleMaxAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public DoubleMaxAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(DOUBLE_MAX_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
