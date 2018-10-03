package in.zapr.druid.druidry.aggregator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
@Getter
@EqualsAndHashCode(callSuper = true)
public class DoubleLastAggregator extends DruidAggregator {
    private static final String DOUBLE_LAST_AGGREGATOR = "doubleLast";
    private String fieldName;

    public DoubleLastAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = DOUBLE_LAST_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}
