package in.zapr.druid.druidry.dimension;

import in.zapr.druid.druidry.dimension.enums.FilteredDimensionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class FilteredDimension extends DruidDimension {
    @NonNull
    protected DimensionSpec delegate;
    @NonNull
    protected FilteredDimensionType type;

}
