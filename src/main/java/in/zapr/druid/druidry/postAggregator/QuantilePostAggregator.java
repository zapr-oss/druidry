package in.zapr.druid.druidry.postAggregator;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilePostAggregator extends DruidPostAggregator {
	
	
	 private static final String QUANTILE_POST_AGGREGATOR_TYPE = "quantile";
	 private String name;
	 private String fieldName;
	 private Double probability;
	 
	 @Builder
	 private QuantilePostAggregator(@NonNull final String name, @NonNull final String fieldName, @NonNull final Double probability){
		this.type = QUANTILE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.probability = probability;
	 }

}
