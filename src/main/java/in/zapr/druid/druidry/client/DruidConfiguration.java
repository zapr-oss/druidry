package in.zapr.druid.druidry.client;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DruidConfiguration {

    private static final int DEFAULT_HTTP_PORT = 80;
    private static final int DEFAULT_HTTPS_PORT = 443;

    private DruidQueryProtocol protocol;
    private String host;
    private Integer port;
    private String endpoint;
    private Integer concurrentConnectionsRequired;

    @Builder
    private DruidConfiguration(DruidQueryProtocol protocol,
                               String host,
                               Integer port,
                               String endpoint,
                               Integer concurrentConnectionsRequired) {

        if (StringUtils.isEmpty(host)) {
            throw new IllegalArgumentException("Host cannot be null or empty");
        }

        if (port != null && port < 0) {
            throw new IllegalArgumentException("Port cannot be negative");
        }

        if (concurrentConnectionsRequired != null && concurrentConnectionsRequired < 1) {
            throw new IllegalArgumentException("Connections required cannot be less than 1");
        }

        if (protocol == null) {
            protocol = DruidQueryProtocol.HTTP;
        }

        if (port == null) {
            port = getDefaultPortOnBasisOfProtocol(protocol);
        }

        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.endpoint = endpoint;
        this.concurrentConnectionsRequired = concurrentConnectionsRequired;
    }

    protected String getUrl() {

        String endpoint = this.getEndpoint();
        if (endpoint == null) {
            endpoint = "";
        }

        return String.format("%s://%s:%d/%s",
                this.getProtocol(),
                this.getHost(),
                this.getPort(),
                endpoint);
    }

    private Integer getDefaultPortOnBasisOfProtocol(DruidQueryProtocol protocol) {

        switch (protocol) {
            case HTTP:
                return DEFAULT_HTTP_PORT;
            case HTTPS:
                return DEFAULT_HTTPS_PORT;
            default:
                throw new IllegalArgumentException("Druid Query Protocol not handled");
        }
    }
}