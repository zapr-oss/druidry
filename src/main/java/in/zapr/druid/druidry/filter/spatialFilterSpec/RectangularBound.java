package in.zapr.druid.druidry.filter.spatialFilterSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RectangularBound extends SpatialFilterBoundSpec {
    private static String SPATIAL_BOUND_TYPE = "rectangular";

    private List<? extends Number> minCoords;
    private List<? extends Number> maxCoords;

    public RectangularBound(@NonNull List<? extends Number> minCoords, @NonNull List<? extends Number> maxCoords) {
        this.type = SPATIAL_BOUND_TYPE;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
    }
}
