package in.zapr.druid.druidry.postAggregator;

import java.util.List;

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
	 private List<Float> breaks;
	 
	 @Builder
	 private CustomBucketsPostAggregator(@NonNull String name, @NonNull String fieldName, @NonNull List<Float> breaks){
		this.type = CUSTOMBUCKET_POST_AGGREGATOR_TYPE;
        this.name = name;
        this.fieldName = fieldName;
        this.breaks = breaks;
	 }

}
