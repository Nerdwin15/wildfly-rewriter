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

/**
 * All rewrite rules in the repository.
 *
 * @author Michael Irwin
 */
public interface RuleRepository {

  /**
   * Retrieve all rules from the repository
   * @return All rewrite rules in the repository
   */
  List<RewriteRule> retrieveAllRules();
  
}
