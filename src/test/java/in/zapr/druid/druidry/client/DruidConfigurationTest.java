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

import org.testng.Assert;
import org.testng.annotations.Test;

public class DruidConfigurationTest {

    @Test
    public void testDruidConfiguration() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .protocol(DruidQueryProtocol.HTTPS)
                .host("druid.zapr.in")
                .port(443)
                .endpoint("druid/v2/")
                .concurrentConnectionsRequired(8)
                .build();

        Assert.assertEquals(config.getProtocol(), DruidQueryProtocol.HTTPS);
        Assert.assertEquals(config.getHost(), "druid.zapr.in");
        Assert.assertEquals(config.getPort().intValue(), 443);
        Assert.assertEquals(config.getEndpoint(), "druid/v2/");
        Assert.assertEquals(config.getConcurrentConnectionsRequired().intValue(), 8);
        Assert.assertEquals(config.getUrl(), "https://druid.zapr.in:443/druid/v2/");
    }

    @Test
    public void testDefaultConfigProtocol() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .host("druid.zapr.in")
                .endpoint("druid/v2/")
                .concurrentConnectionsRequired(8)
                .build();

        Assert.assertEquals(config.getProtocol(), DruidQueryProtocol.HTTP);
        Assert.assertEquals(config.getPort().intValue(), 8082);
        Assert.assertEquals(config.getUrl(), "http://druid.zapr.in:8082/druid/v2/");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativePort() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .host("druid.zapr.in")
                .port(-1)
                .endpoint("druid/v2/")
                .concurrentConnectionsRequired(8)
                .build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeConcurrentConnectionValue() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .host("druid.zapr.in")
                .port(443)
                .endpoint("druid/v2/")
                .concurrentConnectionsRequired(-1)
                .build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNullHost() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .port(443)
                .endpoint("druid/v2/")
                .concurrentConnectionsRequired(14)
                .build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyHost() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .host("")
                .port(443)
                .endpoint("druid/v2/")
                .concurrentConnectionsRequired(16)
                .build();
    }

    @Test
    public void testNullEndpoint() {
        DruidConfiguration config = DruidConfiguration
                .builder()
                .host("druid.zapr.in")
                .port(443)
                .concurrentConnectionsRequired(11)
                .build();

        Assert.assertEquals(config.getUrl(), "http://druid.zapr.in:443/");
    }
}