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

import com.dirkheijnen.mediator.exceptions.HandlerNotFoundException;
import com.dirkheijnen.mediator.interfaces.INotification;
import com.dirkheijnen.mediator.interfaces.INotificationHandler;
import com.dirkheijnen.mediator.interfaces.INotificationHandlerProvider;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  The {@link NotificationHandlerProvider} is used to store the {@link INotification} and {@link INotificationHandler} classes.
 *  Every {@link INotification} is linked to multiple {@link INotificationHandler} using a hashmap.
 *
 *  @author Dirk Heijnen
 *  @since 1.0
 */
public class NotificationHandlerProvider implements INotificationHandlerProvider {

    private final ApplicationContextHelper applicationContextHelper;

    private final Map<Class<? extends INotification>, List<INotificationHandler<?>>> notificationHandlers = new HashMap<>();

    /**
     * The constructor of the {@link RequestHandlerProvider} class.
     *
     * @param applicationContext {@link ApplicationContext};
     */
    public NotificationHandlerProvider(ApplicationContext applicationContext) {
        this.applicationContextHelper = new ApplicationContextHelper(applicationContext);


        for(String notificationHandlerBeanName : applicationContextHelper.getNotificationHandlerBeanNames()){
            this.addNotificationHandler(notificationHandlerBeanName);
        }
    }

    /**
     * Retrieve all the {@link INotificationHandler} for a given {@link INotification} class.
     *
     * @param notification The {@link INotification} class for which all the corresponding {@link INotificationHandler} must be found.
     * @param <T> The type of the notification which must implement the {@link INotification} interface.
     * @return All the {@link INotificationHandler} for the given {@link INotification} class.
     * @throws HandlerNotFoundException If no {@link INotificationHandler} exists for the provided {@link INotification}.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends INotification> List<INotificationHandler<T>> getNotificationHandlers(Class<? extends T> notification) {
        if(notificationHandlers.get(notification) != null) {
            return (List<INotificationHandler<T>>)(Object) notificationHandlers.get(notification);
        } else {
            throw new HandlerNotFoundException("No notification handler exists for the notification: " + notification.getCanonicalName());
        }
    }

    /**
     * Find a given {@link INotificationHandler} by its bean name and stores it in a map with its {@link INotification} as key.
     *
     * @param beanName The name of the bean for the {@link INotificationHandler} to be found.
     */
    @SuppressWarnings("unchecked")
    private void addNotificationHandler(String beanName){
        INotificationHandler<?> notificationHandler = applicationContextHelper.getNotificationHandlerByBeanName(beanName);
        Class<?> notificationHandlerType = applicationContextHelper.getGenericTypeOfNotificationHandler(notificationHandler);
        Class<? extends INotification> notificationType = (Class<? extends INotification>) notificationHandlerType;


        List<INotificationHandler<?>> notificationHandlers;

        if(this.notificationHandlers.containsKey(notificationType)){
            notificationHandlers = this.notificationHandlers.get(notificationType);
        } else {
            notificationHandlers = new ArrayList<>();
        }

        notificationHandlers.add(notificationHandler);
        this.notificationHandlers.put(notificationType, notificationHandlers);
    }
}
