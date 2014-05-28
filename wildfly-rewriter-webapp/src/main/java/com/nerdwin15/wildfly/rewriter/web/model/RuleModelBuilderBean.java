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
package com.nerdwin15.wildfly.rewriter.web.model;

import javax.enterprise.context.RequestScoped;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;
import com.nerdwin15.wildfly.rewriter.web.RuleModel;

/**
 * A {@link RuleModelBuilder} that is a CDI-injectable bean.
 *
 * @author Michael Irwin
 */
@RequestScoped
public class RuleModelBuilderBean implements RuleModelBuilder {

  private RewriteRule rule;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public RuleModelBuilder setRule(RewriteRule rule) {
    this.rule = rule;
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public RuleModel build() {
    return new RuleModelDTO(rule);
  }
  
}
