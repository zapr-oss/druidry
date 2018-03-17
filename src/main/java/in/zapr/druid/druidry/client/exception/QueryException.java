package in.zapr.druid.druidry.client.exception;

public class QueryException extends DruidryException {

    public QueryException(Exception e) {
        super(e);
    }

    public QueryException(String message) {
        super(message);
    }
}
