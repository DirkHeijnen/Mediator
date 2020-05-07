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

package com.dirkheijnen.mediator.exceptions;

import com.dirkheijnen.mediator.interfaces.IRequest;
import com.dirkheijnen.mediator.interfaces.IRequestHandler;

/**
 *  The {@link HandlerNotFoundException} is thrown when no {@link IRequestHandler} is found for a given {@link IRequest}.
 *
 *  @author Dirk Heijnen
 *  @since 1.0
 */
public class HandlerNotFoundException extends RuntimeException {

    /**
     *  Default exception handler without message.
     */
    public HandlerNotFoundException(){
        super();
    }

    /**
     * Exception handler with a custom message.
     * @param message The message of the error.
     */
    public HandlerNotFoundException(String message){
        super(message);
    }

}
