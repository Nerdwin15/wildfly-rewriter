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

import com.nerdwin15.wildfly.rewriter.web.RulesModel;
import com.nerdwin15.wildfly.rewriter.web.service.RuleService;

/**
 * Unit test case for the {@link RewriteRuleEndpoint} class.
 *
 * @author Michael Irwin
 */
public class RewriteRuleEndpointTest {
  
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock RuleService ruleService;
  
  private RewriteRuleEndpoint endpoint = new RewriteRuleEndpoint();
  
  /**
   * Perform setup tasks
   */
  @Before
  public void setUp() {
    endpoint.ruleService = ruleService;
  }
  
  /**
   * Test the getting of all endpoints
   */
  @Test
  public void testGetAllEndpoints() {
    final RulesModel model = context.mock(RulesModel.class);
    context.checking(new Expectations() { { 
      oneOf(ruleService).getAllRules();
        will(returnValue(model));
    } });
    
    Response response = endpoint.getRules();
    assertThat(response.getStatus(), is(200));
    assertThat(response.getEntity(), is(sameInstance((Object) model)));
  }
  
  /**
   * Test the delete rule endpoint
   */
  @Test
  public void testDeleteRuleEndpoint() {
    final Long ruleId = 123L;
    context.checking(new Expectations() { {
      oneOf(ruleService).deleteRule(ruleId);
    } });
    
    Response response = endpoint.deleteRule(ruleId);
    assertThat(response.getStatus(), is(200));
    assertThat(response.getEntity(), is(nullValue()));
  }
}
