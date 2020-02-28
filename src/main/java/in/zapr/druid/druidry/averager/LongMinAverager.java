package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LongMinAverager extends DruidAverager {

    private static final String LONG_MIN_AVERAGER = "longMin";

    public LongMinAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public LongMinAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(LONG_MIN_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
