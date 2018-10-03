package in.zapr.druid.druidry.aggregator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LongFirstAggregator extends DruidAggregator {
    private static final String LONG_FIRST_AGGREGATOR = "longFirst";
    private String fieldName;

    public LongFirstAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = LONG_FIRST_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}
