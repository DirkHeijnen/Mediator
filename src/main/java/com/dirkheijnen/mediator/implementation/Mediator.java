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

package com.dirkheijnen.mediator.implementation;

import com.dirkheijnen.mediator.interfaces.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  The {@link Mediator} is used to send an {@link IRequest} to its {@link IRequestHandler} and call the handle method.
 *  This class is directly injectable in any spring bean.
 *
 *  @author Dirk Heijnen
 *  @since 1.0
 */
@Component
public class Mediator implements IMediator {

    private final IRequestHandlerProvider requestHandlerProvider;
    private final INotificationHandlerProvider notificationHandlerProvider;

    /**
     * The constructor of the {@link Mediator} class.
     *
     * @param applicationContext {@link ApplicationContext};
     */
    public Mediator(ApplicationContext applicationContext){
        this.requestHandlerProvider = new RequestHandlerProvider(applicationContext);
        this.notificationHandlerProvider = new NotificationHandlerProvider(applicationContext);
    }

    /**
     * Sends a given {@link IRequest} to its {@link IRequestHandler} and return the output of the handle method.
     *
     * @param request The {@link IRequest} which should be send to its {@link IRequestHandler}.
     * @param <C> The type of the request which must implement the {@link IRequest} interface.
     * @param <R> The type of the response which must match the type of the {@link IRequest}.
     * @return The return value of the {@link IRequestHandler} handle method.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C extends IRequest<R>, R> R send(C request) {
        IRequestHandler<C, R> requestHandler = requestHandlerProvider.getRequestHandler(request.getClass());
        return requestHandler.handle(request);
    }

    /**
     * Publishes an {@link INotification} to all of its {@link INotificationHandler}
     *
     * @param notification The {@link INotification} which should be send to all of its {@link INotificationHandler}
     * @param <T> The type of the {@link INotification}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends INotification> void publish(T notification) {
        List<INotificationHandler<T>> notificationHandlers = (List<INotificationHandler<T>>)(Object)notificationHandlerProvider.getNotificationHandlers(notification.getClass());
        for (INotificationHandler<T> notificationHandler : notificationHandlers){
            notificationHandler.handle(notification);
        }
    }

}
