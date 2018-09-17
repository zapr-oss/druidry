package in.zapr.druid.druidry.query.search;

import in.zapr.druid.druidry.SortingOrder;
import lombok.Getter;

@Getter
public class SearchSortSpec {

    private SortingOrder type;

    public SearchSortSpec(SortingOrder sortingOrder) {
        this.type = sortingOrder;
    }
}
