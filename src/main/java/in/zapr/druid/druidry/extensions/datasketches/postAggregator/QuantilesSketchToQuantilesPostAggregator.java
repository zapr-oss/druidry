package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilesSketchToQuantilesPostAggregator extends DruidPostAggregator {

    private static final String QUANTILES_SKETCH_TO_QUANTILES_POST_AGGREGATOR_TYPE = "quantilesDoublesSketchToQuantiles";
    private DruidPostAggregator field;
    private double[] fractions;

    @Builder
    private QuantilesSketchToQuantilesPostAggregator(@NonNull String name,
                                                     @NonNull DruidPostAggregator field,
                                                     @NonNull double[] fractions) {
        this.type = QUANTILES_SKETCH_TO_QUANTILES_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
        this.fractions = fractions;
    }

}
