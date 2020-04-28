package in.zapr.druid.druidry.filter.spatialFilterSpec;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RadiusBound extends SpatialFilterBoundSpec {
    private static String SPATIAL_BOUND_TYPE = "radius";

    private List<? extends Number> coords;
    private Number radius;

    public RadiusBound(@NonNull List<? extends Number> coords, @NonNull Number radius) {
        this.type = SPATIAL_BOUND_TYPE;
        this.coords = coords;
        this.radius = radius;
    }

}
