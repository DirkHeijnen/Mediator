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
 * Defines the contract of the {@link INotificationHandler}.
 *
 * @author Dirk Heijnen
 * @since 1.0
 *
 * @param <T> The type of the notification which must implement the {@link INotification} interface.

 */
public interface INotificationHandler<T extends INotification> {

    /**
     * This function encapsulates the logic which should be executed when a given {@link INotification} is send
     * to the {@link INotificationHandler}.
     *
     * @param notification The {@link INotification} containing the input data send with the notification.
     */
    void handle(T notification);

}
