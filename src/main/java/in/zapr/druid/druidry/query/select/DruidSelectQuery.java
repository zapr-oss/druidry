package in.zapr.druid.druidry.query.select;

import com.fasterxml.jackson.annotation.JsonInclude;
import in.zapr.druid.druidry.Context;
import in.zapr.druid.druidry.Interval;
import in.zapr.druid.druidry.dimension.DruidDimension;
import in.zapr.druid.druidry.filter.DruidFilter;
import in.zapr.druid.druidry.granularity.Granularity;
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
public class DruidSelectQuery extends DruidQuery {
    private List<Interval> intervals;
    private DruidFilter filter;
    private Boolean descending;
    private Granularity granularity;
    private List<DruidDimension> dimensions;
    private List<String> metrics;
    private PagingSpec pagingSpec;

    @Builder
    public DruidSelectQuery(
            @NonNull String dataSource,
            DruidFilter filter,
            Boolean descending,
            Granularity granularity,
            List<DruidDimension> dimensions,
            List<String> metrics,
            @NonNull List<Interval> intervals,
            PagingSpec pagingSpec,
            Context context) {
        this.queryType = QueryType.SELECT;
        this.context = context;
        this.dataSource = dataSource;
        this.filter = filter;
        this.descending = descending;
        this.granularity = granularity;
        this.intervals = intervals;
        this.dimensions = dimensions;
        this.metrics = metrics;
        this.pagingSpec = pagingSpec;
    }
}
