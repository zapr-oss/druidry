package in.zapr.druid.druidry.having;

import in.zapr.druid.druidry.filter.DruidFilter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class FilterHaving extends DruidHaving {
    private static String FILTER_DRUID_HAVING_TYPE = "filter";

    protected DruidFilter filter;

    public FilterHaving(@NonNull DruidFilter filter) {
        this.type = FILTER_DRUID_HAVING_TYPE;
        this.filter = filter;
    }
}
