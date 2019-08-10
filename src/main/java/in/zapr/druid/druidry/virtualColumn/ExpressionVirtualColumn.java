package in.zapr.druid.druidry.virtualColumn;

import com.fasterxml.jackson.annotation.JsonInclude;

import in.zapr.druid.druidry.dimension.enums.OutputType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpressionVirtualColumn extends DruidVirtualColumn {
    private static final String EXPRESSION_VIRTUAL_COLUMN = "expression";

    @NonNull
    private String expression;

    @Builder
    public ExpressionVirtualColumn(@NonNull String name, @NonNull String expression, OutputType outputType) {
        this.type = EXPRESSION_VIRTUAL_COLUMN;
        this.name = name;
        this.outputType = outputType;
        this.expression = expression;
    }
}
