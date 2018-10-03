package in.zapr.druid.druidry.aggregator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class FloatLastAggregator extends DruidAggregator {
    private static final String FLOAT_LAST_AGGREGATOR = "floatLast";
    private String fieldName;

    public FloatLastAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = FLOAT_LAST_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}
