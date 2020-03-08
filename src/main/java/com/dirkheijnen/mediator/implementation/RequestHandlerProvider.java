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
import com.dirkheijnen.mediator.exceptions.RequestHandlerAlreadyExistsException;
import com.dirkheijnen.mediator.interfaces.IRequest;
import com.dirkheijnen.mediator.interfaces.IRequestHandler;
import com.dirkheijnen.mediator.interfaces.IRequestHandlerProvider;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 *  The {@link RequestHandlerProvider} is used to store the {@link IRequest} and {@link IRequestHandler} classes.
 *  Every {@link IRequest} is linked to it's {@link IRequestHandler} using a hashmap.
 *
 *  @author Dirk Heijnen
 *  @since 1.0
 */
public class RequestHandlerProvider implements IRequestHandlerProvider {

    private ApplicationContextHelper applicationContextHelper;

    private Map<Class<? extends IRequest<?>>, IRequestHandler<?, ?>> requestHandlers = new HashMap<>();

    /**
     * The constructor of the {@link RequestHandlerProvider} class.
     *
     * @param applicationContext {@link ApplicationContext};
     */
    public RequestHandlerProvider(ApplicationContext applicationContext){
        this.applicationContextHelper = new ApplicationContextHelper(applicationContext);

        for(String requestHandlerBeanName : applicationContextHelper.getRequestHandlerBeanNames()){
            this.addRequestHandler(requestHandlerBeanName);
        }
    }

    /**
     * Retrieve a {@link IRequestHandler} for a given {@link IRequest} class.
     *
     * @param request The {@link IRequest} class for which the corresponding {@link IRequestHandler} must be found.
     * @param <C> The type of the request which must implement the {@link IRequest} interface.
     * @param <R> The type of the response which must match the type of the {@link IRequest}.
     * @return The {@link IRequestHandler} for the given {@link IRequest} class.
     * @throws HandlerNotFoundException If no {@link IRequestHandler} exists for the provided {@link IRequest}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <C extends IRequest<R>, R> IRequestHandler<C, R> getRequestHandler(Class<? extends C> request) {
        if(requestHandlers.get(request) != null){
            return (IRequestHandler<C, R>) requestHandlers.get(request);
        } else {
            throw new HandlerNotFoundException("No request handler exists for the request: " + request.getCanonicalName());
        }
    }

    /**
     * Find a given {@link IRequestHandler} by its bean name and stores it in a map with its {@link IRequest} as key.
     *
     * @param beanName The name of the bean for the {@link IRequestHandler} to be found.
     * @throws RequestHandlerAlreadyExistsException If an {@link IRequestHandler} for the {@link IRequest} already exists.
     */
    @SuppressWarnings("unchecked")
    private void addRequestHandler(String beanName){
        IRequestHandler<?, ?> requestHandler = applicationContextHelper.getRequestHandlerByBeanName(beanName);
        Class<?>[] requestHandlerTypes = applicationContextHelper.getGenericTypesOfRequestHandler(requestHandler);
        Class<? extends IRequest<?>> requestType = (Class<? extends IRequest<?>>) requestHandlerTypes[0];

        if(requestHandlers.containsKey(requestType)){
            String exception = "A handler for request: " + requestType.getCanonicalName() + " already exists";
            throw new RequestHandlerAlreadyExistsException(exception);
        }
        requestHandlers.put(requestType, requestHandler);
    }

}
