package in.zapr.druid.druidry.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThetaSketchEstimatePostAggregator extends DruidPostAggregator{

    private static final String THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE = "thetaSketchEstimate";

    //TODO: Limit field to only two implementation FieldAccess and ThetaSketchOp
    private DruidPostAggregator field;

    @Builder
    private ThetaSketchEstimatePostAggregator(@NonNull String name,
                                              @NonNull DruidPostAggregator field) {
        this.type = THETA_SKETCH_ESTIMATE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
