package in.zapr.druid.druidry.having;

import lombok.NonNull;

import java.util.List;

public class OrHaving extends DruidHaving {
    private static String OR_DRUID_HAVING_TYPE = "or";
    protected List<DruidHaving> havingSpecs;

    public OrHaving(@NonNull List<DruidHaving> fields) {
        this.type = OR_DRUID_HAVING_TYPE;
        this.havingSpecs = fields;
    }
}