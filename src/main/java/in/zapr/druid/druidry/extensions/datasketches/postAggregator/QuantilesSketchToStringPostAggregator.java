package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilesSketchToStringPostAggregator extends DruidPostAggregator {

    private static final String QUANTILES_SKETCH_TO_STRING_POST_AGGREGATOR_TYPE = "quantilesDoublesSketchToString";
    private DruidPostAggregator field;

    @Builder
    private QuantilesSketchToStringPostAggregator(@NonNull String name,
                                                  @NonNull DruidPostAggregator field) {
        this.type = QUANTILES_SKETCH_TO_STRING_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
    }

}
