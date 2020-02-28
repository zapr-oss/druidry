package in.zapr.druid.druidry.averager;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LongMeanAverager extends DruidAverager {

    private static final String LONG_MEAN_AVERAGER = "longMean";

    public LongMeanAverager(@NonNull String name, @NonNull String fieldName, @NonNull Integer buckets) {
        this(name, fieldName, buckets, null);
    }

    public LongMeanAverager(String name, String fieldName, Integer buckets, Integer cycleSize) {
        super(LONG_MEAN_AVERAGER, name, fieldName, buckets, cycleSize);
    }

}
