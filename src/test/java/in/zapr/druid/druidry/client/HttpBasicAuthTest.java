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



public class HttpBasicAuthTest {

    @Test
    public void testHttpBasicAuthWithCredentials() {
        HttpBasicAuth httpBasicAuth = new HttpBasicAuth("user", "secret");

        Assert.assertTrue(
            httpBasicAuth.getAuthFeature().toString()
                .startsWith("org.glassfish.jersey.client.authentication.HttpAuthenticationFeature")
        );
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testHttpBasicAuthWithoutCredentials() {
        new HttpBasicAuth(null, null);
    }

}
