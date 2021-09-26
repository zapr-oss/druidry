package in.zapr.druid.druidry.query.scan;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DruidScanQueryOrder {
    ASCENDING("ascending"),
    DESCENDING("descending"),
    NONE("none");

    private final String value;

    DruidScanQueryOrder(String value) {
        this.value = value;
    }

    @JsonValue
    public String getSortingOrder() {
        return value;
    }
}
