package in.zapr.druid.druidry.postAggregator;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomBucketsPostAggregator extends DruidPostAggregator {
	
	
	 private static final String CUSTOMBUCKET_POST_AGGREGATOR_TYPE = "customBuckets";
	 private String name;
	 private String fieldName;
	 private Set<Double> breaks;
	 
	 @Builder
	 private CustomBucketsPostAggregator(@NonNull final String name, @NonNull final String fieldName, @NonNull final Set<Double> breaks){
		this.type = CUSTOMBUCKET_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.breaks = breaks;
	 }

}
