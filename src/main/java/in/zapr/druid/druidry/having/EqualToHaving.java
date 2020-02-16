package in.zapr.druid.druidry.having;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class EqualToHaving extends DruidHaving {
    private static String EQUAL_TO_DRUID_HAVING_TYPE = "equalTo";
    protected String aggregation;
    protected Object value;

    private EqualToHaving(@NonNull String metric) {
        this.type = EQUAL_TO_DRUID_HAVING_TYPE;
        this.aggregation = metric;
    }

    public EqualToHaving(@NonNull String metric, @NonNull Number value) {
        this(metric);
        this.value = value;
    }

    public EqualToHaving(@NonNull String metric, @NonNull String value) {
        this(metric);
        this.value = value;
    }
}