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

import java.util.List;

/**
 *  Defines the contract of the {@link INotificationHandlerProvider}.
 *
 *  @author Dirk Heijnen
 *  @since 1.0
 */
public interface INotificationHandlerProvider {

    /**
     * Gets all the  {@link INotificationHandler} for a given {@link INotification}.
     *
     * @param notification The {@link INotification} class for which the corresponding {@link INotificationHandler} must be found.
     * @param <T> The type of the notification which must implement the {@link INotification} interface.
     * @return The list of all the found {@link INotificationHandler} for the given {@link INotification}.
     */
    <T extends INotification> List<INotificationHandler<T>> getNotificationHandlers(Class<? extends T> notification);
}
