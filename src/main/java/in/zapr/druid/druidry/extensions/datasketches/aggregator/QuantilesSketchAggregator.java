package in.zapr.druid.druidry.extensions.datasketches.aggregator;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.aggregator.DruidAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilesSketchAggregator extends DruidAggregator {

    private static final String QUANTILES_SKETCH_TYPE_AGGREGATOR = "quantilesDoublesSketch";
    private String fieldName;
    private Integer k;

    @Builder
    private QuantilesSketchAggregator(@NonNull String name,
                                      @NonNull String fieldName,
                                      Integer k) {
        this.type = QUANTILES_SKETCH_TYPE_AGGREGATOR;
        this.name = name;
        this.fieldName = fieldName;
        this.k = k;

        if (k != null) {
            Preconditions.checkArgument(LongMath.isPowerOfTwo(k), "k must be a power of 2");
        }
    }

}
