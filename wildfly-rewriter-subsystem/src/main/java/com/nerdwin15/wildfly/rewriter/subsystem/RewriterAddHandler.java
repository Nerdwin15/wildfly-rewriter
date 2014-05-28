package com.nerdwin15.wildfly.rewriter.subsystem;

import java.util.List;

import org.jboss.as.controller.AbstractBoottimeAddStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ServiceVerificationHandler;
import org.jboss.as.server.AbstractDeploymentChainStep;
import org.jboss.as.server.DeploymentProcessorTarget;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceRegistry;

import com.nerdwin15.wildfly.rewriter.api.ServletContainerHolder;

/**
 * An AddStepHandler that stores a reference to the {@link ServiceRegistry} in
 * the {@link ServletContainerHolder} for later ServletContainer lookup.
 * 
 * @author Michael Irwin
 */
class RewriterAddHandler extends AbstractBoottimeAddStepHandler {

  static final RewriterAddHandler INSTANCE = new RewriterAddHandler();

  private RewriterAddHandler() {
    // Do nothing
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performBoottime(OperationContext context,
      ModelNode operation, ModelNode model,
      ServiceVerificationHandler verificationHandler,
      List<ServiceController<?>> newControllers)
      throws OperationFailedException {
    
    context.addStep(new AbstractDeploymentChainStep() {
      public void execute(DeploymentProcessorTarget processorTarget) {
        processorTarget.addDeploymentProcessor(
            RewriterExtension.SUBSYSTEM_NAME, 
            RewriterDependencyProcessor.PHASE, 
            RewriterDependencyProcessor.PRIORITY, 
            new RewriterDependencyProcessor());
      }
    }, OperationContext.Stage.RUNTIME);

    ServiceRegistry registry = context.getServiceRegistry(false);
    ServletContainerHolder.INSTANCE.setServiceRegistry(registry);
  }

}
