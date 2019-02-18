package in.zapr.druid.druidry.dimension;


import com.fasterxml.jackson.annotation.JsonInclude;
import in.zapr.druid.druidry.dimension.enums.FilteredDimensionType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class PrefixFilteredDimension extends FilteredDimension {
    private String prefix;

    @Builder
    public PrefixFilteredDimension(@NonNull DimensionSpec dimensionSpec, @NonNull String prefix) {
        this.prefix = prefix;
        this.delegate = dimensionSpec;
        this.type = FilteredDimensionType.PREFIX_FILTERED;
    }


}
