package in.zapr.druid.druidry.query.select;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PagingSpec {
    @NonNull
    private Integer threshold;

    private Boolean fromNext;

    @NonNull
    private Map<String, Integer> pagingIdentifiers;
}
