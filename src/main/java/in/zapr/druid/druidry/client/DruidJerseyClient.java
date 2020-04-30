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

import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import in.zapr.druid.druidry.client.exception.ConnectionException;
import in.zapr.druid.druidry.client.exception.QueryException;
import in.zapr.druid.druidry.query.DruidQuery;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DruidJerseyClient implements DruidClient {

    private static final int DEFAULT_CONNECTION_POOL_LIMIT = 5;
    private final String druidUrl;
    private final DruidConfiguration druidConfiguration;

    private ClientConfig jerseyConfig;
    private ThreadLocal<Client> clientThreadLocal = new ThreadLocal<>();
    private WebTarget queryWebTarget;

    public DruidJerseyClient(@NonNull DruidConfiguration druidConfiguration) {
        this(druidConfiguration, null);
    }

    public DruidJerseyClient(@NonNull DruidConfiguration druidConfiguration,
                             ClientConfig jerseyConfig) {

        this.druidUrl = druidConfiguration.getUrl();
        this.jerseyConfig = jerseyConfig;

        log.debug("Will query to {}", druidUrl);
        this.druidConfiguration = druidConfiguration;
    }

    @Override
    public void connect() throws ConnectionException {

        try {
            if (jerseyConfig == null) {

                HttpClientConnectionManager connectionManager = createConnectionManager();
                this.jerseyConfig = new ClientConfig();
                this.jerseyConfig.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
                this.jerseyConfig.connectorProvider(new ApacheConnectorProvider());
            }

            Client client = ClientBuilder.newClient(this.jerseyConfig);
            this.queryWebTarget = client.target(this.druidUrl);
            clientThreadLocal.set(client);
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public void close() throws ConnectionException {
        Client client = clientThreadLocal.get();
        try {
            if (client == null) {
                return;
            }

            client.close();
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public String query(DruidQuery druidQuery) throws QueryException {

        try (Response response = this.queryWebTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(druidQuery, MediaType.APPLICATION_JSON))) {

            if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                handleInternalServerResponse(response);
            }

            return response.readEntity(String.class);

        } catch (QueryException e) {
            log.error("Exception while querying {}", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception while querying {}", e);
            throw new QueryException(e);
        }
    }

    @Override
    public <T> List<T> query(DruidQuery druidQuery, Class<T> className) throws QueryException {
        try (Response response = this.queryWebTarget
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(druidQuery, MediaType.APPLICATION_JSON))) {

            if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                handleInternalServerResponse(response);
            }

            return response.readEntity(new GenericType<List<T>>(TypeUtils.parameterize(List.class, className)) {
            });
        } catch (QueryException e) {
            log.error("Exception while querying {}", e);
            throw e;
        } catch (Exception e) {
            log.error("Exception while querying {}", e);
            throw new QueryException(e);
        }
    }

    private DruidError handleInternalServerResponse(Response response) throws Exception {
        DruidError error = response.readEntity(DruidError.class);
        throw new QueryException(error);
    }

    private HttpClientConnectionManager createConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager
                = new PoolingHttpClientConnectionManager();

        int numberOfConnectionsInPool = DEFAULT_CONNECTION_POOL_LIMIT;

        if (this.druidConfiguration.getConcurrentConnectionsRequired() != null) {
            numberOfConnectionsInPool = this.druidConfiguration.getConcurrentConnectionsRequired();
        }

        connectionManager.setDefaultMaxPerRoute(numberOfConnectionsInPool);
        return connectionManager;
    }
}