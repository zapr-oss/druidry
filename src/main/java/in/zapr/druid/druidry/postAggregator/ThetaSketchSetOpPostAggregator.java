package in.zapr.druid.druidry.postAggregator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;


public class ThetaSketchSetOpPostAggregator extends DruidPostAggregator {

    private static final String THETA_SKETCH_SET_OP_POST_AGGREGATOR_TYPE = "thetaSketchSetOp";

    @JsonProperty("func")
    private  ThetaSketchFunction function;
//TODO: Limit fields to only two implementation FieldAccess and ThetaSketchOp
    private List<DruidPostAggregator> fields;

    @Builder
    private ThetaSketchSetOpPostAggregator(@NonNull String name,
                                           @NonNull ThetaSketchFunction function,
                                           @NonNull List<DruidPostAggregator> fields) {
        this.type = THETA_SKETCH_SET_OP_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.function = function;
        this.fields = fields;
    }

}
