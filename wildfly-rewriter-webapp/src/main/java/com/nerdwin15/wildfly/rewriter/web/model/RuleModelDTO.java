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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;
import com.nerdwin15.wildfly.rewriter.web.RuleModel;

/**
 * An implementation of the {@link RuleModel} class that is annotated for
 * XML/JSON marshalling.
 *
 * @author Michael Irwin
 */
@XmlRootElement(name = "rule")
@XmlType(propOrder = {"id", "from", "to"})
public class RuleModelDTO implements RuleModel {
  
  private Long id;
  private String from;
  private String to;

  /**
   * Create a new, clean instance
   */
  public RuleModelDTO() {
    // Do nothing
  }
  
  /**
   * Create a new instance, based on the provided RewriteRule
   * @param rule
   */
  public RuleModelDTO(RewriteRule rule) {
    this.id = rule.getId();
    this.from = rule.getFrom();
    this.to = rule.getTo();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @XmlAttribute(name = "id")
  public Long getId() {
    return id;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @XmlAttribute(name = "from")
  public String getFrom() {
    return from;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  @XmlAttribute(name = "to")
  public String getTo() {
    return to;
  }
  
}
