package in.zapr.druid.druidry.query.scan;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @Builder
    private DruidScanQuery(@NonNull String dataSource, DruidFilter filter, Integer batchSize, @NonNull List<Interval> intervals, List<DruidDimension> columns, String resultFormat, Context context) {
        this.filter = filter;
        this.batchSize = batchSize;
        this.intervals = intervals;
        this.columns = columns;
        this.resultFormat = resultFormat;
        this.queryType = QueryType.SCAN;
        this.context = context;
        this.dataSource = dataSource;
    }
}
