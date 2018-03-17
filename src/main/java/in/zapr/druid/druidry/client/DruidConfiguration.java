package in.zapr.druid.druidry.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DruidConfiguration {

    private final int DEFAULT_HTTP_PORT = 80;
    private final int DEFAULT_HTTPS_PORT = 443;

    private DruidQueryProtocol protocol = DruidQueryProtocol.HTTP;
    private String host;
    private Integer port;
    private String endpoint;

    protected String getUrl() {

        if (port == null) {
            port = getDefaultPortOnBasisOfProtocol(this.protocol);
        }

        return String.format("%s://%s:%d/%s",
                this.getProtocol(),
                this.getHost(),
                this.getPort(),
                this.getEndpoint());
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