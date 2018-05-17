package in.zapr.druid.druidry.aggregator;

import lombok.NonNull;

public class ThetaSketchAggregator extends DruidAggregator{

    private static final String THETA_SKETCH_TYPE_AGGREGATOR = "thetaSketch";
    private String fieldName;

    public ThetaSketchAggregator(@NonNull String name, @NonNull String fieldName){
        this.type = THETA_SKETCH_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
    }

}
