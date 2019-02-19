package in.zapr.druid.druidry.virtualColumn;

import in.zapr.druid.druidry.dimension.enums.OutputType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public abstract class DruidVirtualColumn {
    @NonNull
    protected String type;

    @NonNull
    protected String name;

    protected OutputType outputType;
}
