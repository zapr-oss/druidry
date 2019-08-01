package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TupleSketchToMeansPostAggregator extends DruidPostAggregator {

    private static final String TUPLE_SKETCH_TO_MEANS_POST_AGGREGATOR_TYPE = "arrayOfDoublesSketchToMeans";
    private DruidPostAggregator field;

    @Builder
    private TupleSketchToMeansPostAggregator(@NonNull String name,
                                             @NonNull DruidPostAggregator field) {
        this.type = TUPLE_SKETCH_TO_MEANS_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
