package in.zapr.druid.druidry.aggregator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LongLastAggregator extends DruidAggregator {
    private static final String LONG_LAST_AGGREGATOR = "longLast";
    private String fieldName;

    public LongLastAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = LONG_LAST_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}
