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

import io.undertow.Handlers;
import io.undertow.server.HandlerWrapper;
import io.undertow.server.HttpHandler;
import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.ServletContainer;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nerdwin15.wildfly.rewriter.api.ServletContainerHolder;

/**
 * An Undertow extension that, when a deployment is made to /ws (will be able 
 * configure this one day), adds the RewriteHttpHandler to the deployment.  
 * 
 * @author Michael Irwin
 */
public class RewriteServletExtension implements ServletExtension {
  
  private static final Logger logger = 
      LoggerFactory.getLogger(RewriteServletExtension.class);
  
  private static final String ADD_LOG = "Adding RewriteHandler to %s at %s";
  
  /**
   * Create a new instance of the extension
   */
  public RewriteServletExtension() {
    logger.debug("RewriteServletExtension has been created");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleDeployment(DeploymentInfo deploymentInfo, 
      ServletContext servletContext) {
    
    if (!servletContext.getContextPath().equals("/ws"))
      return;
    
    logger.info(String.format(ADD_LOG, deploymentInfo.getDeploymentName(), 
        servletContext.getContextPath()));
    
    final ServletContainer servletContainer = 
        ServletContainerHolder.INSTANCE.getServletContainer();
    
    final ConcreteRouteResolutionContainer container = 
        ConcreteRouteResolutionContainer.INSTANCE;
    
    deploymentInfo.addInitialHandlerChainWrapper(new HandlerWrapper() {
      @Override
      public HttpHandler wrap(HttpHandler handler) {
        HttpHandler rewriteHandler = 
            new RewriteHttpHandler(servletContainer, container, handler);
        return Handlers.path().addPrefixPath("/", rewriteHandler);
      }
    });
    
  }
  
}
