package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LongSumAverager extends DruidAverager {

    private static final String LONG_SUM_AVERAGER = "longSum";

    public LongSumAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public LongSumAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(LONG_SUM_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
