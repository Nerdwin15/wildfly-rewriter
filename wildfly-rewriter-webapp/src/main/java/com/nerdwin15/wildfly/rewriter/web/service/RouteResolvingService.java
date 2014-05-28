/*
 * File created on May 28, 2014 
 *
 * Copyright 2013-2014 Nerdwin15, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.nerdwin15.wildfly.rewriter.web.service;

import com.nerdwin15.wildfly.rewriter.RouteResolver;

/**
 * This service is an extension to the {@link RouteResolver} that adds 
 * additional functionality for the web application. 
 * <p>
 * This service is what is used to do the actual routing in the Rewriter
 * subsystem.
 * <p>
 * It is expected that any implementation will store routes in memory to prevent
 * database lookups for each and every incoming request. The refreshRoutes
 * method will be used when a refresh of the internal cache is needed. 
 *
 * @author Michael Irwin
 */
public interface RouteResolvingService extends RouteResolver {

  /**
   * Refresh the internal cache used for routing
   */
  void refreshRoutes();
}
