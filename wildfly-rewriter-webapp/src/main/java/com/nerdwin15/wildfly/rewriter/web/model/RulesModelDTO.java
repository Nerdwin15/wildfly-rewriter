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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nerdwin15.wildfly.rewriter.web.RuleModel;
import com.nerdwin15.wildfly.rewriter.web.RulesModel;

/**
 * Implementation of the {@link RulesModel} that is annotated for XML/JSON
 * marshalling.
 *
 * @author Michael Irwin
 */
@XmlRootElement(name = "rules")
public class RulesModelDTO implements RulesModel {

  private List<RuleModel> rules = new ArrayList<RuleModel>();
  
  /**
   * Create a new, clean instance
   */
  public RulesModelDTO() {
    // Do nothing
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @XmlElement(name = "rule", type = RuleModelDTO.class)
  public RuleModel[] getRules() {
    return rules.toArray(new RuleModel[rules.size()]);
  }
  
  /**
   * Add a new rule model to the model
   * @param ruleModel The model to add
   */
  public void addRule(RuleModel ruleModel) {
    rules.add(ruleModel);
  }
  
}
