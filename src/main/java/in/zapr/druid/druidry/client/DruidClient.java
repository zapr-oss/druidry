package in.zapr.druid.druidry.client;

import in.zapr.druid.druidry.client.exception.ConnectionException;
import in.zapr.druid.druidry.client.exception.QueryException;
import in.zapr.druid.druidry.query.DruidQuery;

public interface DruidClient {

    /**
     * Connects with Druid
     *
     * @throws ConnectionException
     */
    void connect() throws ConnectionException;

    /**
     * Closes connection with Druid
     * @throws ConnectionException
     */
    void close() throws ConnectionException;

    /**
     * Queries druid
     *
     * @param druidQuery Druid Query object
     * @return Result from Druid
     * @throws QueryException
     */
    String query(DruidQuery druidQuery) throws QueryException;

    /**
     * Queries druid
     *
     * @param druidQuery Druid Query Object
     * @param className Class according to which DruidResult should be converted to
     * @param <T> Class according to which DruidResult should be converted to
     * @return Druid Result in the form of class T object
     * @throws QueryException
     */
    <T> T query(DruidQuery druidQuery, T className) throws QueryException;
}