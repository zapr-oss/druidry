package in.zapr.druid.druidry.filter.spatialFilterSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class PolygonBound extends SpatialFilterBoundSpec {
    private static String SPATIAL_BOUND_TYPE = "polygon";

    private List<? extends Number> abscissa;
    private List<? extends Number> ordinate;

    public PolygonBound(@NonNull List<? extends Number> abscissa, @NonNull List<? extends Number> ordinate) {
        this.type = SPATIAL_BOUND_TYPE;
        this.abscissa = abscissa;
        this.ordinate = ordinate;
    }
}
