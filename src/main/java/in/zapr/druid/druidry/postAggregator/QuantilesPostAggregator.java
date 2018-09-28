package in.zapr.druid.druidry.postAggregator;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilesPostAggregator extends DruidPostAggregator {
	
	
	 private static final String QUANTILE_POST_AGGREGATOR_TYPE = "quantiles";
	 private String name;
	 private String fieldName;
	 private Set<Float> probabilities;
	 
	 @Builder
	 private QuantilesPostAggregator(@NonNull String name, @NonNull String fieldName, @NonNull Set<Float> probabilities){
		this.type = QUANTILE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.probabilities = probabilities;
	 }

}
