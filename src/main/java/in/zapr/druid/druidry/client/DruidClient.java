package in.zapr.druid.druidry.client;

import java.util.List;

import in.zapr.druid.druidry.client.exception.ConnectionException;
import in.zapr.druid.druidry.client.exception.QueryException;
import in.zapr.druid.druidry.query.DruidQuery;

public interface DruidClient {

    /**
     * Connects with Druid
     *
     * @throws ConnectionException When connection is not formed
     */
    void connect() throws ConnectionException;

    /**
     * Closes connection with Druid
     * @throws ConnectionException When connection is not closed
     */
    void close() throws ConnectionException;

    /**
     * Queries druid
     *
     * @param druidQuery Druid Query object
     * @return Result from Druid
     * @throws QueryException Error while querying
     */
    String query(DruidQuery druidQuery) throws QueryException;

    /**
     * Queries druid
     *
     * @param druidQuery Druid Query Object
     * @param className Class according to which DruidResult should be converted to
     * @param <T> Class according to which DruidResult should be converted to
     * @return Druid Result in the form of class T object
     * @throws QueryException Error while querying
     */
    <T> List<T> query(DruidQuery druidQuery, Class<T> className) throws QueryException;
}