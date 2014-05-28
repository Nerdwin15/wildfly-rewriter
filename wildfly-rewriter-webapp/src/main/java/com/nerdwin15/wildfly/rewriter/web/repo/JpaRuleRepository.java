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

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nerdwin15.wildfly.rewriter.web.RewriteRule;
import com.nerdwin15.wildfly.rewriter.web.RuleModel;
import com.nerdwin15.wildfly.rewriter.web.domain.RewriteRuleEntity;

/**
 * Implementation of the {@link RuleRepository} that uses JPA.
 *
 * @author Michael Irwin
 */
@ApplicationScoped
public class JpaRuleRepository implements RuleRepository {

  @PersistenceContext
  private EntityManager entityManager;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public RewriteRule createRule(RuleModel ruleModel) {
    RewriteRuleEntity entity = new RewriteRuleEntity();
    entity.setFrom(ruleModel.getFrom());
    entity.setTo(ruleModel.getTo());
    entityManager.persist(entity);
    return entity;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public List<RewriteRule> retrieveAllRules() {
    List<RewriteRule> rules = entityManager
        .createNamedQuery("retrieveAllRules", RewriteRule.class)
        .getResultList();
    return rules;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteRule(Long ruleId) {
    entityManager
        .createNamedQuery("deleteRuleById")
        .setParameter("id", ruleId)
        .executeUpdate();
  }
  
}
