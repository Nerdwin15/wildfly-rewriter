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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;
import com.nerdwin15.wildfly.rewriter.web.RulesModel;
import com.nerdwin15.wildfly.rewriter.web.model.RulesModelBuilder;
import com.nerdwin15.wildfly.rewriter.web.repo.RuleRepository;

/**
 * Unit test case for the {@link RuleServiceBean} class.
 *
 * @author Michael Irwin
 */
public class RuleServiceBeanTest {
  
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  @Mock RuleRepository ruleRepository;
  @Mock RulesModelBuilder rulesModelBuilder;
  
  private RuleServiceBean bean = new RuleServiceBean();
  
  /**
   * Perform setup tasks
   */
  @Before
  public void setUp() {
    bean.ruleRepository = ruleRepository;
    bean.rulesModelBuilder = rulesModelBuilder;
  }
  
  /**
   * Test the retrieval of all rules
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testGetAllRules() {
    final List<RewriteRule> rules = context.mock(List.class);
    final RulesModel model = context.mock(RulesModel.class);
    
    context.checking(new Expectations() { { 
      oneOf(ruleRepository).retrieveAllRules();
        will(returnValue(rules));
      oneOf(rulesModelBuilder).setRules(rules);
        will(returnValue(rulesModelBuilder));
      oneOf(rulesModelBuilder).build();
        will(returnValue(model));
    } });
    
    assertThat(bean.getAllRules(), is(sameInstance((Object) model)));
  }
  
  /**
   * Test the deletion of a rule
   */
  @Test
  public void testDeleteRule() {
    final Long ruleId = 123L;
    context.checking(new Expectations() { { 
      oneOf(ruleRepository).deleteRule(ruleId);
    } });

    bean.deleteRule(ruleId);
  }
}
