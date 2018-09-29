package in.zapr.druid.druidry.extensions.datasketches.aggregator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import in.zapr.druid.druidry.aggregator.DruidAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThetaSketchAggregator extends DruidAggregator {

    private static final String THETA_SKETCH_TYPE_AGGREGATOR = "thetaSketch";
    private String fieldName;
    private Boolean isInputThetaSketch;
    private Long size;

    @Builder
    public ThetaSketchAggregator(
            @NonNull String name,
            @NonNull String fieldName,
            Boolean isInputThetaSketch,
            Long size){
        this.type = THETA_SKETCH_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
        this.isInputThetaSketch = isInputThetaSketch;
        this.size = size;

        if(size != null) {
            Preconditions.checkArgument(LongMath.isPowerOfTwo(size), "size must be a power of 2");
        }
    }

}
