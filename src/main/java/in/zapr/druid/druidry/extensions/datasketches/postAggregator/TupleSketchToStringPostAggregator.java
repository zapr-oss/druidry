package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TupleSketchToStringPostAggregator extends DruidPostAggregator {

    private static final String TUPLE_SKETCH_TO_STRING_AGGREGATOR_TYPE = "arrayOfDoublesSketchTTest";
    private DruidPostAggregator field;

    @Builder
    private TupleSketchToStringPostAggregator(@NonNull String name,
                                              @NonNull DruidPostAggregator field) {
        this.type = TUPLE_SKETCH_TO_STRING_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
