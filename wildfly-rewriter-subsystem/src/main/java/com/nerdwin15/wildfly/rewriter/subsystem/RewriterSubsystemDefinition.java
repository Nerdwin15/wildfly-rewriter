package com.nerdwin15.wildfly.rewriter.subsystem;

import org.jboss.as.controller.SimpleResourceDefinition;

/**
 * Definition of subsystem=wildfly-rewriter
 */
public class RewriterSubsystemDefinition 
    extends SimpleResourceDefinition {
  
  public static final RewriterSubsystemDefinition INSTANCE = 
      new RewriterSubsystemDefinition();

  private RewriterSubsystemDefinition() {
    super(RewriterExtension.SUBSYSTEM_PATH,
        RewriterExtension.getResourceDescriptionResolver("subsystem"),
        RewriterAddHandler.INSTANCE,
        RewriterRemoveHandler.INSTANCE);
  }
    
}
