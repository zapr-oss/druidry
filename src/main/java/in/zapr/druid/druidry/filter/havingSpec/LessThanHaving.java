package in.zapr.druid.druidry.filter.havingSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class LessThanHaving extends DruidHaving {
    private static String LESS_THAN_DRUID_HAVING_TYPE = "lessThan";
    protected String aggregation;
    protected Object value;

    private LessThanHaving(@NonNull String metric) {
        this.type = LESS_THAN_DRUID_HAVING_TYPE;
        this.aggregation = metric;
    }

    public LessThanHaving(@NonNull String metric, @NonNull Number value) {
        this(metric);
        this.value = value;
    }
    
    public LessThanHaving(@NonNull String metric, @NonNull String value) {
        this(metric);
        this.value = value;
    }

}
