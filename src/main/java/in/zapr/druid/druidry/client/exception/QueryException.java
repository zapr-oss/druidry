package in.zapr.druid.druidry.client.exception;

import in.zapr.druid.druidry.client.DruidError;
import lombok.Getter;

public class QueryException extends DruidryException {

    @Getter
    private DruidError druidError;

    public QueryException(Exception e) {
        super(e);
    }

    public QueryException(String message) {
        super(message);
    }

    public QueryException(DruidError error) {
        super(error.getErrorMessage());
        this.druidError = error;
    }
}
