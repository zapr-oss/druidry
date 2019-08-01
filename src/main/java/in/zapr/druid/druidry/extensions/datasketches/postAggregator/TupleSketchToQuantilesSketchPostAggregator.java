package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TupleSketchToQuantilesSketchPostAggregator extends DruidPostAggregator {

    private static final String TUPLE_SKETCH_TO_QUANTILES_SKETCH_AGGREGATOR_TYPE = "arrayOfDoublesSketchToQuantilesSketch";
    private DruidPostAggregator field;
    private Integer column;
    private Integer k;

    @Builder
    private TupleSketchToQuantilesSketchPostAggregator(@NonNull String name,
                                                       @NonNull DruidPostAggregator field,
                                                       Integer column,
                                                       Integer k) {
        this.type = TUPLE_SKETCH_TO_QUANTILES_SKETCH_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
        this.column = column;
        this.k = k;
    }
    
}
