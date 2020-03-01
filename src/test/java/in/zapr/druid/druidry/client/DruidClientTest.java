package in.zapr.druid.druidry.client;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import in.zapr.druid.druidry.query.DruidQuery;
import org.testng.annotations.Test;

import java.util.List;

public class DruidClientTest {

    private boolean wasClientClosed;

    @Test
    public void testAutoClosing() {
        assertFalse(wasClientClosed, "Client closing indicator is not initialized properly");
        try (TestDruidClient client = new TestDruidClient()) {
            client.connect();
        }
        assertTrue(wasClientClosed, "Close method has not been called");
    }

    private class TestDruidClient implements DruidClient {

        @Override
        public void connect() {

        }

        @Override
        public void close() {
            wasClientClosed = true;
        }

        @Override
        public String query(DruidQuery druidQuery) {
            return null;
        }

        @Override
        public <T> List<T> query(DruidQuery druidQuery, Class<T> className) {
            return null;
        }

    }

}
