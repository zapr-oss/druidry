package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilesSketchToHistogramPostAggregator extends DruidPostAggregator {

    private static final String QUANTILES_SKETCH_TO_HISTOGRAM_POST_AGGREGATOR_TYPE = "quantilesDoublesSketchToHistogram";
    private DruidPostAggregator field;
    private double[] splitPoints;

    @Builder
    private QuantilesSketchToHistogramPostAggregator(@NonNull String name,
                                                     @NonNull DruidPostAggregator field,
                                                     @NonNull double[] splitPoints) {
        this.type = QUANTILES_SKETCH_TO_HISTOGRAM_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.field = field;
        this.splitPoints = splitPoints;
    }

}
