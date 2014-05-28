/*
 * File created on May 23, 2014 
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
package com.nerdwin15.wildfly.rewriter.subsystem;

import org.jboss.as.server.deployment.Attachments;
import org.jboss.as.server.deployment.DeploymentPhaseContext;
import org.jboss.as.server.deployment.DeploymentUnit;
import org.jboss.as.server.deployment.DeploymentUnitProcessingException;
import org.jboss.as.server.deployment.DeploymentUnitProcessor;
import org.jboss.as.server.deployment.Phase;
import org.jboss.as.server.deployment.module.ModuleDependency;
import org.jboss.as.server.deployment.module.ModuleSpecification;
import org.jboss.modules.Module;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.modules.ModuleLoader;

/**
 * A {@link DeploymentUnitProcessor} that adds the various module dependencies
 * to a deployment.
 *
 * @author Michael Irwin
 */
public class RewriterDependencyProcessor implements DeploymentUnitProcessor {

  private static final ModuleIdentifier REWRITER_ADAPTER = 
      ModuleIdentifier.create("com.nerdwin15.wildfly.rewriter.wildfly-rewriter-adapter");
  private static final ModuleIdentifier REWRITER_ADAPTER_API = 
      ModuleIdentifier.create("com.nerdwin15.wildfly.rewriter.wildfly-rewriter-adapter-api");
  private static final ModuleIdentifier REWRITER_CORE = 
      ModuleIdentifier.create("com.nerdwin15.wildfly.rewriter.wildfly-rewriter-core");
  
  public static final Phase PHASE = Phase.DEPENDENCIES;
  public static final int PRIORITY = 0;

  /**
   * {@inheritDoc}
   */
  @Override
  public void deploy(DeploymentPhaseContext phaseContext) throws DeploymentUnitProcessingException {
    final DeploymentUnit deploymentUnit = phaseContext.getDeploymentUnit();
    addModules(deploymentUnit);
  }
  
  private void addModules(DeploymentUnit deploymentUnit) {
    final ModuleSpecification moduleSpecification = 
        deploymentUnit.getAttachment(Attachments.MODULE_SPECIFICATION);
    addDependency(moduleSpecification, REWRITER_ADAPTER_API, false);
    addDependency(moduleSpecification, REWRITER_ADAPTER, true);
    addDependency(moduleSpecification, REWRITER_CORE, false);
  }

  @Override
  public void undeploy(DeploymentUnit context) {
    // Nothing to do here
  }

  private void addDependency(ModuleSpecification spec, ModuleIdentifier id, 
      boolean importServices) {
    ModuleLoader loader = Module.getBootModuleLoader();
    spec.addSystemDependency(
        new ModuleDependency(loader, id, false, false, importServices, false));
  }
  
}
