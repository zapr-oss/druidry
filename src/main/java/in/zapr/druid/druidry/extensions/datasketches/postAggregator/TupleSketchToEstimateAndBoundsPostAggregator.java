package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TupleSketchToEstimateAndBoundsPostAggregator extends DruidPostAggregator {

    private static final String TUPLE_SKETCH_TO_ESTIMATE_AND_BOUNDS_POST_AGGREGATOR_TYPE = "arrayOfDoublesSketchToEstimateAndBounds";
    private DruidPostAggregator field;
    private Integer numStdDevs;

    @Builder
    private TupleSketchToEstimateAndBoundsPostAggregator(@NonNull String name,
                                                        @NonNull DruidPostAggregator field,
                                                        Integer numStdDevs) {
        this.type = TUPLE_SKETCH_TO_ESTIMATE_AND_BOUNDS_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
        this.numStdDevs = numStdDevs;
    }

}
