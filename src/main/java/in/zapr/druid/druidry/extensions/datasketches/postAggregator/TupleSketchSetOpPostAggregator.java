package in.zapr.druid.druidry.extensions.datasketches.postAggregator;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TupleSketchSetOpPostAggregator extends DruidPostAggregator {

    private static final String TUPLE_SKETCH_SET_OP_POST_AGGREGATOR_TYPE = "arrayOfDoublesSketchSetOp";
    private TupleSketchOperation operation;
    private List<DruidPostAggregator> fields;
    private Integer nominalEntries;
    private Integer numberOfValues;

    @Builder
    private TupleSketchSetOpPostAggregator(@NonNull String name,
                                           @NonNull TupleSketchOperation operation,
                                           @NonNull List<DruidPostAggregator> fields,
                                           Integer nominalEntries,
                                           Integer numberOfValues) {
        this.type = TUPLE_SKETCH_SET_OP_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.operation = operation;
        this.fields = fields;
        this.nominalEntries = nominalEntries;
        this.numberOfValues = numberOfValues;

        if (nominalEntries != null) {
            Preconditions.checkArgument(LongMath.isPowerOfTwo(nominalEntries), "nominalEntries must be a power of 2");
        }
    }

}
