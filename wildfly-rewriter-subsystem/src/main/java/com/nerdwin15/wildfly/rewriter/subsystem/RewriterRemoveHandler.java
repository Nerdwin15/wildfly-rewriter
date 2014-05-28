package com.nerdwin15.wildfly.rewriter.subsystem;

import org.jboss.as.controller.AbstractRemoveStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.dmr.ModelNode;

/**
 * Handler that does really nothing. When it's time to remove the subsystem,
 * the ServletContainerHolder will also be going away, so there's nothing to
 * really clean up.
 *
 * @author Michael Irwin
 */
class RewriterRemoveHandler extends AbstractRemoveStepHandler {

  static final RewriterRemoveHandler INSTANCE = new RewriterRemoveHandler();

  private RewriterRemoveHandler() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performRuntime(OperationContext context, ModelNode operation, 
      ModelNode model) throws OperationFailedException {
    // Nothing to do here
  }


}
