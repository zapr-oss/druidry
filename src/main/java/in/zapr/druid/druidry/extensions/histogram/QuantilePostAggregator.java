package in.zapr.druid.druidry.extensions.histogram;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.postAggregator.DruidPostAggregator;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuantilePostAggregator extends DruidPostAggregator {
	
	
	 private static final String QUANTILE_POST_AGGREGATOR_TYPE = "quantile";
	 private String name;
	 private String fieldName;
	 private Float probability;
	 
	 @Builder
	 private QuantilePostAggregator(@NonNull String name, @NonNull String fieldName, @NonNull Float probability){
		this.type = QUANTILE_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.probability = probability;
	 }

}
