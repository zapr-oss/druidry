package in.zapr.druid.druidry.filter.havingSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class OrHaving extends DruidHaving {
    private static String OR_DRUID_HAVING_TYPE = "or";
    protected List<DruidHaving> havingSpecs;

    public OrHaving(@NonNull List<DruidHaving> fields) {
        this.type = OR_DRUID_HAVING_TYPE;
        this.havingSpecs = fields;
    }
}