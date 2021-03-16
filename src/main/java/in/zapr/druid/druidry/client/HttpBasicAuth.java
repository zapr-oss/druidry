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

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class HttpBasicAuth {

    @NonNull
    private final String username;

    @NonNull
    private final String password;

    HttpAuthenticationFeature getAuthFeature() {
        return HttpAuthenticationFeature
                .basicBuilder()
                .credentials(username, password)
                .build();
    }
}
