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