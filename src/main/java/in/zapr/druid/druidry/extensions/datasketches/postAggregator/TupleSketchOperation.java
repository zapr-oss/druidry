package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TupleSketchOperation {

    INTERSECT("INTERSECT"),
    UNION("UNION"),
    NOT("NOT");

    private String value;

    TupleSketchOperation(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
