package in.zapr.druid.druidry.postAggregator;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ThetaSketchEstimatePostAggregator extends DruidPostAggregator{

    private static final String THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE = "thetaSketchEstimate";

    private DruidPostAggregator field;

    public ThetaSketchEstimatePostAggregator(@NonNull String name,
                                              @NonNull DruidPostAggregator field) {
        this.type = THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
