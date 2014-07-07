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
package com.nerdwin15.wildfly.rewriter.web.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nerdwin15.wildfly.rewriter.web.RuleModel;
import com.nerdwin15.wildfly.rewriter.web.RulesModel;
import com.nerdwin15.wildfly.rewriter.web.service.event.RuleChangeEvent;

/**
 * An implementation of the {@link RouteResolvingService}.
 *
 * @author Michael Irwin
 */
@ApplicationScoped
public class RouteResolvingServiceBean implements RouteResolvingService {
  
  private static final Logger logger = 
      LoggerFactory.getLogger(RouteResolvingServiceBean.class);
  
  @Inject
  protected RuleService ruleService;
  
  private RulesModel rules;
  
  @PostConstruct
  public void postConstruct() {
    refreshRoutes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String resolveRoute(String requestURI) {
    for (RuleModel rule : rules.getRules()) {
      if (requestURI.startsWith(rule.getFrom())) {
        return requestURI.replace(rule.getFrom(), rule.getTo());
      }
    }
    return null;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshRoutes() {
    rules = ruleService.getAllRules();
  }
  
  public void onRuleUpdate(@Observes RuleChangeEvent event) {
    logger.info("Refreshing routes due to rule change - " + event.getType());
    refreshRoutes();
  }
  
}
