package in.zapr.druid.druidry.dimension;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import in.zapr.druid.druidry.dimension.enums.FilteredDimensionType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class ListFilteredDimension extends FilteredDimension {
    protected List<String> values;
    @JsonProperty(value = "isWhitelist")
    protected Boolean whitelist;

    @Builder
    public ListFilteredDimension(@NonNull DimensionSpec dimensionSpec, @NonNull List<String> values, Boolean whitelist) {
        this.delegate = dimensionSpec;
        this.type = FilteredDimensionType.LIST_FILTERED;
        this.values = values;
        this.whitelist = whitelist;
    }

}
