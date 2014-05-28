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

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;
import com.nerdwin15.wildfly.rewriter.web.RuleModel;
import com.nerdwin15.wildfly.rewriter.web.RulesModel;
import com.nerdwin15.wildfly.rewriter.web.model.RuleModelBuilder;
import com.nerdwin15.wildfly.rewriter.web.model.RulesModelBuilder;
import com.nerdwin15.wildfly.rewriter.web.repo.RuleRepository;
import com.nerdwin15.wildfly.rewriter.web.service.event.RuleChangeEvent;
import com.nerdwin15.wildfly.rewriter.web.service.event.RuleChangeEvent.Type;

/**
 * Implementation of the {@link RuleService} that is a CDI-injectable bean.
 *
 * @author Michael Irwin
 */
@Stateless
public class RuleServiceBean implements RuleService {

  @Inject
  protected RuleRepository ruleRepository;
  
  @Inject
  protected RuleModelBuilder ruleModelBuilder;
  
  @Inject
  protected RulesModelBuilder rulesModelBuilder;
  
  @Inject
  protected Event<RuleChangeEvent> changeEvent;
  
  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public RulesModel getAllRules() {
    List<RewriteRule> rules = ruleRepository.retrieveAllRules();
    return rulesModelBuilder.setRules(rules).build();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void deleteRule(Long ruleId) {
    ruleRepository.deleteRule(ruleId);
    changeEvent.fire(new RuleChangeEvent(Type.DELETE, null));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public RuleModel addRule(RuleModel rule) {
    RewriteRule rewriteRule = ruleRepository.createRule(rule);
    changeEvent.fire(new RuleChangeEvent(Type.ADD, rewriteRule));
    return ruleModelBuilder.setRule(rewriteRule).build();
  }
  
}
