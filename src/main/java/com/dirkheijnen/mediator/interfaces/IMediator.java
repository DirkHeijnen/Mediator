/*
 * Copyright 2020 the original author or authors.
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

package com.dirkheijnen.mediator.interfaces;

/**
 * The interface which describes the contract of the {@link IMediator}
 *
 * @author Dirk Heijnen
 * @since 1.0
 */
public interface IMediator {

    /**
     * Sends an {@link IRequest} to its {@link IRequestHandler}
     *
     * @param request The {@link IRequest} which should be send to its {@link IRequestHandler}
     * @param <C> The type of the {@link IRequest}
     * @param <R> The response type of the {@link IRequest} and {@link IRequestHandler}
     * @return The response set as the type of the {@link IRequest}
     */
    <C extends IRequest<R>, R> R send(C request);

    /**
     * Publishes an {@link INotification} to all of its {@link INotificationHandler}
     *
     * @param notification The {@link INotification} which should be send to all of its {@link INotificationHandler}
     * @param <T> The type of the {@link INotification}
     */
    <T extends INotification> void publish(T notification);

}
