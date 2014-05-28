/*
 * File created on May 27, 2014 
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
package com.nerdwin15.wildfly.rewriter.adapter;

import com.nerdwin15.wildfly.rewriter.RouteResolutionContainer;
import com.nerdwin15.wildfly.rewriter.RouteResolver;

/**
 * A concrete implementation of the {@link RouteResolutionContainer}.
 *
 * @author Michael Irwin
 */
public class ConcreteRouteResolutionContainer 
    implements RouteResolutionContainer {
  
  public static final ConcreteRouteResolutionContainer INSTANCE = 
      new ConcreteRouteResolutionContainer();
  
  private RouteResolver routeResolver;
  
  private ConcreteRouteResolutionContainer() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearRouteResolver() {
    routeResolver = null;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void setRouteResolver(RouteResolver routeResolver) {
    this.routeResolver = routeResolver;
  }
  
  /**
   * Gets the {@code routeResolver} property.
   */
  public RouteResolver getRouteResolver() {
    return routeResolver;
  }
  
}
