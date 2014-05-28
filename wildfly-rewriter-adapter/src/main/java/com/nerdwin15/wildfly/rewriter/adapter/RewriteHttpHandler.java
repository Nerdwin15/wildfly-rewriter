/*
 * File created on May 22, 2014 
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

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.api.Deployment;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link HttpHandler} that checks if a reroute needs to happen and if so,
 * performs the forward to the correct context.
 *
 * @author Michael Irwin
 */
public class RewriteHttpHandler implements HttpHandler {
  
  private static final Logger logger = 
      LoggerFactory.getLogger(RewriteHttpHandler.class);
  private static final String REWRITE_LOG_MESSAGE = "Rewrote %s to %s";
  
  public static final String ORIGINAL_URI_HEADER = "X-Original-Uri";
  public static final String ORIGINAL_URL_HEADER = "X-Original-Url";
  
  private ServletContainer servletContainer;
  private ConcreteRouteResolutionContainer resolutionService;
  private HttpHandler fallbackHandler;
  
  /**
   * Create a new instance
   * @param servletContainer The ServletContainer used to forward requests
   * @param resolutionService A service used to perform route resolution
   * @param fallbackHandler An HttpHandler used for fallback if a rewrite
   * doesn't occur.
   */
  public RewriteHttpHandler(ServletContainer servletContainer, 
      ConcreteRouteResolutionContainer resolutionService,
      HttpHandler fallbackHandler) {
    this.servletContainer = servletContainer;
    this.resolutionService = resolutionService;
    this.fallbackHandler = fallbackHandler;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleRequest(HttpServerExchange exchange) throws Exception {
    String currentRoute = exchange.getRequestURI();
    if (resolutionService.getRouteResolver() == null) {
      logger.warn("Unable to handle rewrite exchange - no resolver configured");
      return;
    }
    
    String newRoute = resolutionService.getRouteResolver().resolveRoute(currentRoute);
    if (newRoute != null) {
      reroute(exchange, currentRoute, newRoute);
    }
    else {
      logger.debug("No forwarding route found for " + currentRoute);
      fallbackHandler.handleRequest(exchange);
    }
  }
  
  private void reroute(HttpServerExchange exchange, String from, String to) 
      throws Exception {
    String oldUri = exchange.getRequestURI();
    
    addRewrittenHeaders(exchange);
    
    // The request URI still contains the full URI, including the application
    // context path.
    exchange.setRequestURI(exchange.getRequestURI().replace(from, to));
    
    // Remove the application context, as its "already resolved" when passed on
    // to the deployment
    exchange.setRelativePath(exchange.getRequestURI().substring(to.indexOf("/", 1)));

    logger.trace(String.format(REWRITE_LOG_MESSAGE, oldUri, exchange.getRequestURI()));
    logger.trace("New relative path is " + exchange.getRelativePath());
    
    Deployment deployment = servletContainer.getDeploymentByPath(exchange.getRequestURI()).getDeployment();
    deployment.getHandler().handleRequest(exchange);
  }
  
  private void addRewrittenHeaders(HttpServerExchange exchange) {
    HeaderMap headers = exchange.getRequestHeaders();
    headers.add(new HttpString(ORIGINAL_URI_HEADER), exchange.getRequestURI());
    headers.add(new HttpString(ORIGINAL_URL_HEADER), exchange.getRequestURL());
  }
  
}
