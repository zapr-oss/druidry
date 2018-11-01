/*
 * Copyright 2018-present Red Brick Lane Marketing Solutions Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
     *
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
     * @param className  Class according to which DruidResult should be converted to
     * @param <T>        Class according to which DruidResult should be converted to
     * @return Druid Result in the form of class T object
     * @throws QueryException Error while querying
     */
    <T> List<T> query(DruidQuery druidQuery, Class<T> className) throws QueryException;
}