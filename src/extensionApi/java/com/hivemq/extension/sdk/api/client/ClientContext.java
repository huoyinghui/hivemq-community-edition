/*
 * Copyright 2018 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extension.sdk.api.client;

import com.hivemq.extension.sdk.api.interceptor.publish.PublishInboundInterceptor;
import com.hivemq.extension.sdk.api.packets.auth.ModifiableDefaultPermissions;
import com.hivemq.extension.sdk.api.annotations.DoNotImplement;
import com.hivemq.extension.sdk.api.annotations.Immutable;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.interceptor.Interceptor;

import java.util.List;

/**
 * The client context is used to set up all interceptors for a client.
 * <p>
 * The client context is only valid until the initialize methods have returned.
 *
 * @author Christoph Schäbel
 * @author Florian Limpöck
 * @since 4.0.0
 */
@DoNotImplement
public interface ClientContext {

    /**
     * Adds an {@link PublishInboundInterceptor} for this client. <br>
     * Subsequent adding of the same interceptor will be ignored.
     *
     * @param publishInboundInterceptor The implementation of an PublishInboundInterceptor.
     * @since 4.0.0
     */
    void addPublishInboundInterceptor(@NotNull PublishInboundInterceptor publishInboundInterceptor);

    /**
     * Removes an {@link PublishInboundInterceptor} for this client. <br>
     * Nothing happens if the interceptor that should be removed, has not been added in the first place.
     *
     * @param publishInboundInterceptor The implementation of an PublishInboundInterceptor.
     * @since 4.0.0
     */
    void removePublishInboundInterceptor(@NotNull PublishInboundInterceptor publishInboundInterceptor);

    /**
     * Returns all {@link Interceptor} which are registered for this client.
     *
     * @return List of Interceptors for this client.
     * @since 4.0.0
     */
    @Immutable
    @NotNull List<@NotNull Interceptor> getAllInterceptors();

    /**
     * Returns all {@link PublishInboundInterceptor} which are registered for this client by this extension.
     *
     * @return List of PublishInboundInterceptors for this client.
     * @since 4.0.0
     */
    @Immutable
    @NotNull List<@NotNull PublishInboundInterceptor> getPublishInboundInterceptors();

    /**
     * The default permissions for this client. Default permissions are automatically applied by HiveMQ for every
     * MQTT PUBLISH and SUBSCRIBE packet sent by this client.
     *
     * @return The {@link ModifiableDefaultPermissions}.
     * @since 4.0.0
     */
    @NotNull ModifiableDefaultPermissions getDefaultPermissions();
}
