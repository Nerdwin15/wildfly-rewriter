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
package com.nerdwin15.wildfly.rewriter.web.repo;

import java.util.List;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;
import com.nerdwin15.wildfly.rewriter.web.RuleModel;

/**
 * All rewrite rules in the repository.
 *
 * @author Michael Irwin
 */
public interface RuleRepository {
  
  /**
   * Create a new rewrite rule and return the new domain object
   * @param ruleModel The model of the rule to create
   * @return The new domain object
   */
  RewriteRule createRule(RuleModel ruleModel);

  /**
   * Retrieve all rules from the repository
   * @return All rewrite rules in the repository
   */
  List<RewriteRule> retrieveAllRules();
 
  /**
   * Delete the rule with the provided id
   * @param ruleId The id of the rewrite rule to remove.
   */
  void deleteRule(Long ruleId);
}
