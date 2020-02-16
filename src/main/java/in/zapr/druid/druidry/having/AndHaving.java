package in.zapr.druid.druidry.having;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class AndHaving extends DruidHaving {
    private static String AND_DRUID_HAVING_TYPE = "and";
    private List<DruidHaving> havingSpecs;

    public AndHaving(@NonNull List<DruidHaving> fields) {
        this.type = AND_DRUID_HAVING_TYPE;
        this.havingSpecs = fields;
    }
}