package in.zapr.druid.druidry.query.search;

import in.zapr.druid.druidry.SortingOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class SearchSortSpec {

    private SortingOrder type;

    public SearchSortSpec(SortingOrder sortingOrder) {
        this.type = sortingOrder;
    }
}
