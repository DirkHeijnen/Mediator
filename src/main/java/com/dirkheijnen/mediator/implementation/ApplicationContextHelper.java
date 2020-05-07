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

import com.dirkheijnen.mediator.interfaces.INotification;
import com.dirkheijnen.mediator.interfaces.INotificationHandler;
import com.dirkheijnen.mediator.interfaces.IRequest;
import com.dirkheijnen.mediator.interfaces.IRequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

/**
 *  Helper methods for accessing the {@link ApplicationContext}.
 *
 *  @author Dirk Heijnen
 *  @since 1.0
 */
public class ApplicationContextHelper {

    private ApplicationContext applicationContext;

    /**
     * Constructor for the {@link ApplicationContextHelper} class.
     *
     * @param applicationContext The spring {@link ApplicationContext} of the current container.
     */
    public ApplicationContextHelper(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    /**
     * Retrieves all the bean names for all registered {@link IRequestHandler} beans.
     *
     * @return An array of {@link String} which contains all the names of the {@link IRequestHandler} beans.
     */
    public String[] getRequestHandlerBeanNames(){
        return applicationContext.getBeanNamesForType(IRequestHandler.class);
    }

    /**
     * Retrieves all the bean names for all registered {@link INotificationHandler} beans.
     *
     * @return An array of {@link String} which contains all the names of the {@link INotificationHandler} beans.
     */
    public String[] getNotificationHandlerBeanNames(){
        return applicationContext.getBeanNamesForType(INotificationHandler.class);
    }

    /**
     * Retrieves the bean object for the given bean name and casts it to an {@link IRequestHandler}.
     *
     * @param beanName The name of the bean for the {@link IRequestHandler} to be found.
     * @return The {@link IRequestHandler} matching the provided bean name.
     */
    public IRequestHandler<?, ?> getRequestHandlerByBeanName(String beanName){
        return (IRequestHandler<? ,?>) applicationContext.getBean(beanName);
    }

    /**
     * Retrieves the bean object for the given bean name and casts it to an {@link INotificationHandler}.
     *
     * @param beanName The name of the bean for the {@link INotificationHandler} to be found.
     * @return The {@link INotificationHandler} matching the provided bean name.
     */
    public INotificationHandler<?> getNotificationHandlerByBeanName(String beanName){
        return (INotificationHandler<?>) applicationContext.getBean(beanName);
    }

    /**
     * Retrieves the generic types of the {@link IRequestHandler}.
     * This will have 2 entries:
     *      [0] = The {@link IRequest} type.
     *      [1] = The response type of the {@link IRequest}.
     *
     * @param requestHandler The {@link IRequestHandler} for which the generic types must be found.
     * @return An array of the generics types of the provided {@link IRequestHandler}.
     */
    public Class<?>[] getGenericTypesOfRequestHandler(IRequestHandler<?, ?> requestHandler){
        return GenericTypeResolver.resolveTypeArguments(requestHandler.getClass(), IRequestHandler.class);
    }

    /**
     * Retrieves the generic type of the {@link INotificationHandler}.
     *
     * @param notificationHandler The {@link IRequestHandler} for which the generic types must be found.
     * @return The generic type of the provided {@link INotificationHandler}.
     */
    public Class<?> getGenericTypeOfNotificationHandler(INotificationHandler<?> notificationHandler) {
        return GenericTypeResolver.resolveTypeArgument(notificationHandler.getClass(), INotificationHandler.class);
    }

}
