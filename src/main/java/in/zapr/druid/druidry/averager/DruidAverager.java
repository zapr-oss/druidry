package in.zapr.druid.druidry.averager;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public abstract class DruidAverager {

    @NonNull
    String type;
    @NonNull
    String name;
    @NonNull
    String fieldName;
    @NonNull
    Integer buckets;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer cycleSize;

}
