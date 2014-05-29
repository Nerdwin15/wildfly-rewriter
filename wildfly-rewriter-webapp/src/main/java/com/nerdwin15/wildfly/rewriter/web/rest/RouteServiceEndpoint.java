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
import javax.ws.rs.core.Response.Status;

import com.nerdwin15.wildfly.rewriter.web.service.RouteResolvingService;

/**
 * A controller used for endpoints related to the routing service.
 *
 * @author Michael Irwin
 */
@Path("/routing")
public class RouteServiceEndpoint {

  @Inject
  protected RouteResolvingService routeService;
  
  /**
   * Refresh the rules used by the routing service
   * @return A response
   */
  @POST
  @Path("/refresh")
  public Response refreshRules() {
    routeService.refreshRoutes();
    return Response.ok().build();
  }
  
  /**
   * An endpoint that allows one to get the result for a provided request URI. 
   * @param requestURI
   * @return A 204 if no rewrite is found. Otherwise, a 200 with the rewritten
   * URI in the body.
   */
  @POST
  @Path("/test")
  public Response testPath(String requestURI) {
    String newPath = routeService.resolveRoute(requestURI);
    if (newPath == null)
      return Response.status(Status.NO_CONTENT).build();
    return Response.ok(newPath).build();
  }
  
}
