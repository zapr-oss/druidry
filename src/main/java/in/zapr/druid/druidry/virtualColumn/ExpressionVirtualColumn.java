package in.zapr.druid.druidry.virtualColumn;

import javax.annotation.Nonnull;

import in.zapr.druid.druidry.dimension.enums.OutputType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ExpressionVirtualColumn extends DruidVirtualColumn {
    private static final String EXPRESSION_VIRTUAL_COLUMN = "expression";

    @NonNull
    private String expression;

    public ExpressionVirtualColumn(@NonNull String name, @Nonnull String expression) {
        this(name, expression, OutputType.FLOAT);
    }

    public ExpressionVirtualColumn(@NonNull String name, @Nonnull String expression, OutputType outputType) {
        this.type = EXPRESSION_VIRTUAL_COLUMN;
        this.name = name;
        this.outputType = outputType;
        this.expression = expression;
    }
}
