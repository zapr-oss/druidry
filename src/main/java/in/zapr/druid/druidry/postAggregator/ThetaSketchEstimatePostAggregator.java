package in.zapr.druid.druidry.postAggregator;

import lombok.Builder;
import lombok.NonNull;


public class ThetaSketchEstimatePostAggregator extends DruidPostAggregator{

    private static final String THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE = "thetaSketchEstimate";

//TODO: Limit fields to only two implementation FieldAccess and ThetaSketchOp
    private DruidPostAggregator field;

    @Builder
    private ThetaSketchEstimatePostAggregator(@NonNull String name,
                                              @NonNull DruidPostAggregator field) {
        this.type = THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
