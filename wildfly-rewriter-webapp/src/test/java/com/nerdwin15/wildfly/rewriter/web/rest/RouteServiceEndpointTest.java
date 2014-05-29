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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;

import javax.ws.rs.core.Response;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.nerdwin15.wildfly.rewriter.web.service.RouteResolvingService;

/**
 * Unit test case for the {@link RouteServiceEndpoint} class.
 *
 * @author Michael Irwin
 */
public class RouteServiceEndpointTest {
  
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock RouteResolvingService routeService;
  
  private RouteServiceEndpoint endpoint = new RouteServiceEndpoint();
  
  /**
   * Perform setup tasks
   */
  @Before
  public void setUp() {
    endpoint.routeService = routeService;
  }
  
  /**
   * Test the refresh routes endpoint
   */
  @Test
  public void testRefreshRoutes() {
    context.checking(new Expectations() { { 
      oneOf(routeService).refreshRoutes();
    } });
    
    Response response = endpoint.refreshRules();
    assertThat(response.getStatus(), is(200));
    assertThat(response.getEntity(), is(nullValue()));
  }

  /**
   * Test the rewrite test endpoint when a rule isn't found.
   * It is expected that a 204 is returned, with no returning URI.
   */
  @Test
  public void testRewriteTestEndpointWithNoRewriteFound() {
    final String requestURI = "/ws/something/another";
    context.checking(new Expectations() { { 
      oneOf(routeService).resolveRoute(requestURI);
      will(returnValue(null));
    } });
    
    Response response = endpoint.testPath(requestURI);
    assertThat(response.getStatus(), is(204));
    assertThat(response.getEntity(), is(nullValue()));
  }
  
  /**
   * Test the rewrite test endpoint when a rewrite rule is found.
   * It is expected that a 200 is returned, with the rewritten URI in the body.
   */
  @Test
  public void testRewriteTestEndpointWithRewriteFound() {
    final String requestURI = "/ws/something/another";
    final String newURI = "/otherApp/api/value";
    context.checking(new Expectations() { { 
      oneOf(routeService).resolveRoute(requestURI);
      will(returnValue(newURI));
    } });
    
    Response response = endpoint.testPath(requestURI);
    assertThat(response.getStatus(), is(200));
    assertThat(response.getEntity(), is(sameInstance((Object) newURI)));
  }
  
}
