package in.zapr.druid.druidry.having;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class NotHaving extends DruidHaving {
    private static String NOT_DRUID_HAVING_TYPE = "not";
    protected DruidHaving havingSpec;

    public NotHaving(@NonNull DruidHaving field) {
        this.type = NOT_DRUID_HAVING_TYPE;
        this.havingSpec = field;
    }
}