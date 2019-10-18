package in.zapr.druid.druidry.having;

import lombok.NonNull;

import java.util.List;

public class NotHaving extends DruidHaving {
    private static String NOT_DRUID_HAVING_TYPE = "not";
    protected List<DruidHaving> havingSpecs;

    public NotHaving(@NonNull List<DruidHaving> fields) {
        this.type = NOT_DRUID_HAVING_TYPE;
        this.havingSpecs = fields;
    }
}