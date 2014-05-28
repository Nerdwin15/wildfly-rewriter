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
package com.nerdwin15.wildfly.rewriter.web;

import java.util.ServiceLoader;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.nerdwin15.wildfly.rewriter.RouteResolutionContainerProvider;
import com.nerdwin15.wildfly.rewriter.RouteResolver;
import com.nerdwin15.wildfly.rewriter.web.service.RouteResolvingService;

/**
 * A {@link ServletContextListener} that sets this webapp as the 
 * {@link RouteResolver} for rewriting
 *
 * @author Michael Irwin
 */
@WebListener
public class RouteResolutionSetupWebListener implements ServletContextListener {

  @Inject RouteResolvingService routeResolver;
  
  private static ServiceLoader<RouteResolutionContainerProvider> provider = 
      ServiceLoader.load(RouteResolutionContainerProvider.class);
  private static RouteResolutionContainerProvider resolutionProvider;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void contextInitialized(ServletContextEvent event) {
    resolutionProvider = provider.iterator().next();
    if (resolutionProvider != null) {
      System.out.println("!*!*!* Set the route resolver");
      resolutionProvider.get().setRouteResolver(routeResolver);
    }
    else {
      System.out.println("!*!*!* Unable to find a resolutionProvider");
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void contextDestroyed(ServletContextEvent event) {
    if (resolutionProvider != null) {
      resolutionProvider.get().clearRouteResolver();
    }
  }
  
}
