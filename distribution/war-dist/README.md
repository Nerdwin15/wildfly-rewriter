For installation, the following needs to be performed:

1. In your Wildfly's base folder, unpack the modules-[version].zip folder.

2. In your standalone.xml, add the extension:

    ```
    <extensions>
      ...
      <extension module="com.nerdwin15.wildfly.rewriter.wildfly-rewriter-subsystem"/>
    </extensions>
    ```
    
3. Add the subsystem:

    ```
    <subsystem xmlns="urn:com.nerwin15.wildfly.rewriter:1.0"/>
    ```
    
4. Deploy the webapp.  That's it!