package in.zapr.druid.druidry.aggregator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThetaSketchAggregator extends DruidAggregator{

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
    }

}
