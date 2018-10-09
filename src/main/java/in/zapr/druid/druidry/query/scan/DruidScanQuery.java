package in.zapr.druid.druidry.query.scan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;
import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.query.DruidQuery;
import in.zapr.druid.druidry.query.QueryType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class DruidScanQuery extends DruidQuery {

    private DruidFilter filter;
    private Integer batchSize;
    private List<Interval> intervals;
    private List<DruidDimension> columns;
    private String resultFormat;
    private Long limit;
    private Boolean legacy;

    @Builder
    private DruidScanQuery(@NonNull String dataSource, DruidFilter filter, Integer batchSize, @NonNull List<Interval> intervals, List<DruidDimension> columns, String resultFormat, Long limit, Boolean legacy, Context context) {
        this.filter = filter;
        this.intervals = intervals;
        this.columns = columns;
        this.resultFormat = resultFormat;
        this.queryType = QueryType.SCAN;
        this.context = context;
        this.dataSource = dataSource;
        this.legacy = legacy;

        if (limit != null) {
            Preconditions.checkArgument(limit > 0, "limit specified must be more than 0");
            this.limit = limit;
        }
        if (batchSize != null) {

            Preconditions.checkArgument(batchSize > 0, "batchSize specified must be more than 0");
            this.batchSize = batchSize;
        }


    }
}
