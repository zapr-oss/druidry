package in.zapr.druid.druidry.having;

import lombok.NonNull;

import java.util.List;

public class AndHaving extends DruidHaving {
    private static String AND_DRUID_HAVING_TYPE = "and";
    private List<DruidHaving> havingSpecs;

    public AndHaving(@NonNull List<DruidHaving> fields) {
        this.type = AND_DRUID_HAVING_TYPE;
        this.havingSpecs = fields;
    }
}