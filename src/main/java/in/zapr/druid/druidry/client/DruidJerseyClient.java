package in.zapr.druid.druidry.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import in.zapr.druid.druidry.client.exception.ConnectionException;
import in.zapr.druid.druidry.client.exception.QueryException;
import in.zapr.druid.druidry.query.DruidQuery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DruidJerseyClient implements DruidClient {

    private final Client client;
    private final String url;

    public DruidJerseyClient(DruidConfiguration druidConfiguration) {

        this.client = ClientBuilder.newClient();
        this.url = druidConfiguration.getUrl();
        log.debug("Will query to {}", url);
    }

    @Override
    public void connect() throws ConnectionException {

    }

    @Override
    public void close() throws ConnectionException {
        this.client.close();
    }

    @Override
    public String query(DruidQuery druidQuery) throws QueryException {

        try (Response response = this.client
                .target(this.url)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(druidQuery, MediaType.APPLICATION_JSON))) {

            if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                handleInternalServerResponse(response);
            }

            return response.readEntity(String.class);

        } catch (Exception e) {
            throw new QueryException(e);
        }
    }

    @Override
    public <T> List<T> query(DruidQuery druidQuery, Class<T> className) throws QueryException {
        try (Response response = this.client
                .target(this.url)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(druidQuery, MediaType.APPLICATION_JSON))) {

            if (response.getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
                handleInternalServerResponse(response);
            }

            return response.readEntity(new GenericType<List<T>>() {
            });
        } catch (Exception e) {
            log.error("Exception while querying {}", e);
            throw new QueryException(e);
        }
    }

    private void handleInternalServerResponse(Response response) throws QueryException {
        String message = response.readEntity(String.class);
        throw new QueryException(message);
    }
}