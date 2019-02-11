package in.zapr.druid.druidry.dimension.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FilteredDimensionType {

    @JsonProperty(value = "listFiltered")
    LIST_FILTERED,
    @JsonProperty(value = "regexFiltered")
    REGEX_FILTERED,
    @JsonProperty(value = "prefixFiltered")
    PREFIX_FILTERED
}
