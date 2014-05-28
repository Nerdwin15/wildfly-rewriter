package com.nerdwin15.wildfly.rewriter.subsystem;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.ADD;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIBE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP_ADDR;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;

import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

import org.jboss.as.controller.Extension;
import org.jboss.as.controller.ExtensionContext;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.SubsystemRegistration;
import org.jboss.as.controller.descriptions.StandardResourceDescriptionResolver;
import org.jboss.as.controller.operations.common.GenericSubsystemDescribeHandler;
import org.jboss.as.controller.parsing.ExtensionParsingContext;
import org.jboss.as.controller.parsing.ParseUtils;
import org.jboss.as.controller.persistence.SubsystemMarshallingContext;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.as.controller.registry.OperationEntry;
import org.jboss.dmr.ModelNode;
import org.jboss.staxmapper.XMLElementReader;
import org.jboss.staxmapper.XMLElementWriter;
import org.jboss.staxmapper.XMLExtendedStreamReader;
import org.jboss.staxmapper.XMLExtendedStreamWriter;


/**
 * Wildfly Subsystem extension for the rewriting subsystem
 * 
 * @author Michael Irwin
 */
public class RewriterExtension implements Extension {

  public static final String NAMESPACE = "urn:com.nerwin15.wildfly.rewriter:1.0";
  public static final String SUBSYSTEM_NAME = "rewriter";

  private static final String RESOURCE_NAME = 
      RewriterExtension.class.getPackage().getName() + ".LocalDescriptions";

  protected static final PathElement SUBSYSTEM_PATH = 
      PathElement.pathElement(SUBSYSTEM, SUBSYSTEM_NAME);
  
  static StandardResourceDescriptionResolver getResourceDescriptionResolver(
      final String keyPrefix) {
    String prefix = SUBSYSTEM_NAME + (keyPrefix == null ? "" : "." + keyPrefix);
    return new StandardResourceDescriptionResolver(prefix, RESOURCE_NAME, 
        RewriterExtension.class.getClassLoader(), true, false);
  }
  
  /**
   * The parser used for parsing our subsystem
   */
  private final SubsystemParser parser = new SubsystemParser();

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeParsers(ExtensionParsingContext context) {
      context.setSubsystemXmlMapping(SUBSYSTEM_NAME, NAMESPACE, parser);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initialize(ExtensionContext context) {
    final SubsystemRegistration subsystem = 
        context.registerSubsystem(SUBSYSTEM_NAME, 1, 0);
    final ManagementResourceRegistration registration = 
        subsystem.registerSubsystemModel(RewriterSubsystemDefinition.INSTANCE);
    registration.registerOperationHandler(DESCRIBE, 
        GenericSubsystemDescribeHandler.INSTANCE, 
        GenericSubsystemDescribeHandler.INSTANCE, 
        false, OperationEntry.EntryType.PRIVATE);

    subsystem.registerXMLElementWriter(parser);
  }

  private static ModelNode createAddSubsystemOperation() {
      final ModelNode subsystem = new ModelNode();
      subsystem.get(OP).set(ADD);
      subsystem.get(OP_ADDR).add(SUBSYSTEM, SUBSYSTEM_NAME);
      return subsystem;
  }

  /**
   * The subsystem parser, which uses stax to read and write to and from xml
   */
  private static class SubsystemParser implements XMLStreamConstants, 
    XMLElementReader<List<ModelNode>>, 
    XMLElementWriter<SubsystemMarshallingContext> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeContent(XMLExtendedStreamWriter writer, 
        SubsystemMarshallingContext context) throws XMLStreamException {
      context.startSubsystemElement(RewriterExtension.NAMESPACE, false);
      writer.writeEndElement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readElement(XMLExtendedStreamReader reader, 
        List<ModelNode> list) throws XMLStreamException {
      // Require no content
      ParseUtils.requireNoContent(reader);
      list.add(createAddSubsystemOperation());
    }
  }

}
