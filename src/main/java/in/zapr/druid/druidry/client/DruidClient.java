/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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