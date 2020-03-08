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
 * Defines the contract of the {@link IRequestHandler}.
 *
 * @author Dirk Heijnen
 * @since 1.0
 *
 * @param <C> The type of the request which must implement the {@link IRequest} interface.
 * @param <R> The type of the response which must match the type of the {@link IRequest}.
 */
public interface IRequestHandler<C extends IRequest<R>, R> {

    /**
     * This function encapsulates the logic which should be executed when a given {@link IRequest} is send
     * to the {@link IRequestHandler}.
     *
     * @param request The {@link IRequest} containing the input data send with the request.
     * @return The response type which is set on the {@link IRequest}.
     */
    R handle(C request);

}
