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

import com.nerdwin15.wildfly.rewriter.web.RuleModel;
import com.nerdwin15.wildfly.rewriter.web.RulesModel;

/**
 * Defines a service that is used to interact with rewrite rules.
 *
 * @author Michael Irwin
 */
public interface RuleService {

  /**
   * Save the incoming rule as a new rewriting rule
   * @param rule The rule to save
   * @return The updated rule
   */
  RuleModel addRule(RuleModel rule);
  
  /**
   * Get all rewrite rules in the system
   * @return All rewrite rules in the system.
   */
  RulesModel getAllRules();
  
  /**
   * Delete the rewrite rule based on the provided id
   * @param ruleId The id of the rule to remove
   */
  void deleteRule(Long ruleId);
  
}
