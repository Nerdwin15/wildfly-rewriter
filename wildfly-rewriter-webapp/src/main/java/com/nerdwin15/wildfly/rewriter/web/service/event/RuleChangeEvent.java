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
package com.nerdwin15.wildfly.rewriter.web.service.event;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;

/**
 * A CDI-event that describes when a rule has been changed.
 *
 * @author Michael Irwin
 */
public class RuleChangeEvent {
  
  public enum Type {
    ADD, REMOVE, DELETE
  }
  
  private final Type type;
  private final RewriteRule rule;
  
  public RuleChangeEvent(Type type, RewriteRule rule) {
    this.type = type;
    this.rule = rule;
  }
  
  /**
   * Gets the {@code rule} property.
   */
  public RewriteRule getRule() {
    return rule;
  }
  
  /**
   * Gets the {@code type} property.
   */
  public Type getType() {
    return type;
  }

}
