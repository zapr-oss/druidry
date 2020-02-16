package in.zapr.druid.druidry.filter.havingSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DimSelectorHaving extends DruidHaving {
    private static String DIM_SELECTOR_DRUID_HAVING_TYPE = "dimSelector";
    protected String dimension;
    protected Object value;


    private DimSelectorHaving(@NonNull String dimension) {
        this.type = DIM_SELECTOR_DRUID_HAVING_TYPE;
        this.dimension = dimension;
    }

    public DimSelectorHaving(@NonNull String dimension, Number value) {
        this(dimension);
        this.value = value;
    }

    public DimSelectorHaving(@NonNull String dimension, String value) {
        this(dimension);
        this.value = value;
    }
}