package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilesSketchToQuantilePostAggregator extends DruidPostAggregator {

    private static final String QUANTILES_SKETCH_TO_QUANTILE_POST_AGGREGATOR_TYPE = "quantilesDoublesSketchToQuantile";
    private DruidPostAggregator field;
    private double fraction;

    @Builder
    private QuantilesSketchToQuantilePostAggregator(@NonNull String name,
                                                    @NonNull DruidPostAggregator field,
                                                    @NonNull double fraction) {
        this.type = QUANTILES_SKETCH_TO_QUANTILE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
        this.fraction = fraction;
    }

}
