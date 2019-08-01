package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TupleSketchTTestPostAggregator extends DruidPostAggregator {

    private static final String TUPLE_SKETCH_TTEST_AGGREGATOR_TYPE = "arrayOfDoublesSketchTTest";
    private List<DruidPostAggregator> fields;

    @Builder
    private TupleSketchTTestPostAggregator(@NonNull String name,
                                           @NonNull List<DruidPostAggregator> fields) {
        this.type = TUPLE_SKETCH_TTEST_AGGREGATOR_TYPE;
        this.name = name;
        this.fields = fields;
    }

}
