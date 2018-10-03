package in.zapr.druid.druidry.aggregator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class FloatFirstAggregator extends DruidAggregator {
    private static final String FLOAT_FIRST_AGGREGATOR = "floatFirst";
    private String fieldName;

    public FloatFirstAggregator(@NonNull String name, @NonNull String fieldName) {
        this.type = FLOAT_FIRST_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }
}
