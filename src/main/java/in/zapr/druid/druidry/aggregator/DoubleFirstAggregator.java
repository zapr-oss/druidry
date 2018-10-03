package in.zapr.druid.druidry.aggregator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleFirstAggregator extends DruidAggregator {
    private static final String DOUBLE_FIRST_AGGREGATOR = "doubleFirst";
    private String fieldName;

    public DoubleFirstAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = DOUBLE_FIRST_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}
