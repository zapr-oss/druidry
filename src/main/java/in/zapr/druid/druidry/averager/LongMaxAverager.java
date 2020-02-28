package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LongMaxAverager extends DruidAverager {

    private static final String LONG_MAX_AVERAGER = "longMax";

    public LongMaxAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public LongMaxAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(LONG_MAX_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
