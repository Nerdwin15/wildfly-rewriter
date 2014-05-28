Wildfly Rewriter
================

This repository contains a subsystem and web application that allows for URL rewriting in Wildfly.  

## Motivation/Use Case

In many situations, one has the desire to deploy various REST-based services that are spread across many deployed artifacts.  However, one may not want to expose the project name, deployment name, etc. as it's either ugly or makes no sense to the consumer of the service.

This URL rewriter allows one to build a consistent taxonomy of names and resources and redirect the request to the appropriate application, without the need of using Apache Httpd, nginx, or anything else in front of it.

Also, it provides a web-based application that provides for easy management of the rewrite rules. Here's a few reasons we chose this route:

- By having it web-based, it's easily maintained through a web interface.
- By using a database to back it, it's easier to back up and keep clustered servers consistent with their rewrite rules. 
- By keeping rewrite logic out of the subsystem, one only needs to redeploy a webapp to update the smarts. No more server bounces required!

## Installation

To install, download the full distribution (link coming soon... can simply run ```mvn clean install``` for right now).

1. In the root of your Wildfly instance, unpack the modules-[version].zip folder. This will unzip the subsystem and modules into your Wildfly instance.

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

4. Start up your server and deploy the web application.


## How it Works

- An Undertow ServletExtension adds a custom HttpHandler (_RewriteHttpHandler_) around requests for a web service application
- When requests are made for the web services application, a _RouteResolver_ is given the chance to rewrite the incoming URL.
- If a rewrite is requested, the RewriteHttpHandler forwards the request to the appropriate Undertow Deployment.

To allow for easy configuration, a simple webapp is included that registers itself as a _RouteResolver_. This allows the rewrite logic to be outside the subsystem and module, meaning updates without server bounces, easier clustering support, and more.

## Extending the Rewriter

Currently, the _RewriteHttpHandler_ is added only to a deployment that is placed at the _/ws_ context path.  However, anything can be declared as a _RouteResolver_.  But, only one resolver is allowed at any time and the last one set wins.

To declare yourself a _RouteResolver_, use the ServiceLoader and load a _RouteResolutionContainerProvider_. The container provided in the provider is the same as that used by the handler.

## Contributing

If you wish to contribute, you're more than welcome to do so! Here are a few ideas we have of things to be done and just haven't gotten to.  Feel free to take an issue if you wish!

- Add subsystem configuration that'll allow one to configure the application root to attach the handler to (right now, it's hard-coded at /ws)
- Creation of the admin interface
- Security constraints on the admin interface
