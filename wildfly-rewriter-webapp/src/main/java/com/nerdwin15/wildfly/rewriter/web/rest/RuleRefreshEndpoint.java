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
package com.nerdwin15.wildfly.rewriter.web.rest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.nerdwin15.wildfly.rewriter.web.service.RouteResolvingService;

/**
 * DESCRIBE THE TYPE HERE.
 *
 * @author Michael Irwin
 */
@Path("/rules/refresh")
public class RuleRefreshEndpoint {

  @Inject
  protected RouteResolvingService routeService;
  
  /**
   * Refresh the rules used by the routing service
   * @return A response
   */
  @POST
  public Response refreshRules() {
    routeService.refreshRoutes();
    return Response.ok().build();
  }
}
