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
package com.nerdwin15.wildfly.rewriter.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;

/**
 * An entity representation of the {@link RewriteRule}, using JPA.
 *
 * @author Michael Irwin
 */
@Entity
@Table(name = "rewrite_rules")
public class RewriteRuleEntity implements RewriteRule {

  private Long id;
  private Long version;
  private String from;
  private String to;
  
  /**
   * {@inheritDoc}
   */
  @Id
  @GeneratedValue
  @Override
  public Long getId() {
    return id;
  }
  
  /**
   * Sets the {@code id} property.
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * Gets the {@code version} property.
   */
  @Version
  public Long getVersion() {
    return version;
  }
  
  /**
   * Sets the {@code version} property.
   */
  public void setVersion(Long version) {
    this.version = version;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Column(name = "url_from")
  public String getFrom() {
    return from;
  }
  
  /**
   * Sets the {@code from} property.
   */
  public void setFrom(String from) {
    this.from = from;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Column(name = "url_to")
  public String getTo() {
    return to;
  }
  
  /**
   * Sets the {@code to} property.
   */
  public void setTo(String to) {
    this.to = to;
  }

}
