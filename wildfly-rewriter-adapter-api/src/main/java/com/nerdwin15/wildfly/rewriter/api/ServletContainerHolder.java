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
package com.nerdwin15.wildfly.rewriter.api;

import io.undertow.servlet.api.ServletContainer;

import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceRegistry;
import org.wildfly.extension.undertow.ServletContainerService;
import org.wildfly.extension.undertow.UndertowService;

/**
 * A singleton holder that provides an instance to a {@link ServletContainer}.
 * <p>
 * To prevent loading order issues, the container stores a reference to the
 * {@link ServiceRegistry} and extracts the ServletContainer upon first request.
 *
 * @author Michael Irwin
 */
public class ServletContainerHolder {

  public static final ServletContainerHolder INSTANCE = 
      new ServletContainerHolder();
  
  private ServiceRegistry serviceRegistry; 
  private ServletContainer servletContainer;

  private ServletContainerHolder() {
    // Do nothing
  }
  
  /**
   * Gets the {@code servletContainer} property.
   */
  public ServletContainer getServletContainer() {
    if (servletContainer == null) {
      servletContainer = extractServletContainer();
      if (servletContainer == null) {
        throw new IllegalStateException("Unable to extract a ServletContainer");
      }
    }
    return servletContainer;
  }
  
  /**
   * Sets the {@code servletContainer} property.
   */
  public void setServiceRegistry(ServiceRegistry serviceRegistry) {
    this.serviceRegistry = serviceRegistry;
  }

  @SuppressWarnings("unchecked")
  private ServletContainer extractServletContainer() {
    ServiceName name = UndertowService.SERVLET_CONTAINER.append("default");
    ServiceController<ServletContainerService> controller = 
        (ServiceController<ServletContainerService>)
        serviceRegistry.getService(name);
    
    if (controller != null) {
      ServletContainerService service = controller.getService().getValue();
      return service.getServletContainer();
    }
    else {
      throw new IllegalStateException("No servlet container service found");
    }
  }
  
}
