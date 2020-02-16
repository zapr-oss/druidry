package in.zapr.druid.druidry.filter.havingSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class GreaterThanHaving extends DruidHaving {
    private static String GREATER_THAN_DRUID_HAVING_TYPE = "greaterThan";
    protected String aggregation;
    protected Object value;

    private GreaterThanHaving(@NonNull String metric) {
        this.type = GREATER_THAN_DRUID_HAVING_TYPE;
        this.aggregation = metric;
    }

    public GreaterThanHaving(@NonNull String metric, @NonNull Number value) {
        this(metric);
        this.value = value;
    }

    public GreaterThanHaving(@NonNull String metric, @NonNull String value) {
        this(metric);
        this.value = value;
    }
}
