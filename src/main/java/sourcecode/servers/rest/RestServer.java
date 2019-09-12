package sourcecode.servers.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import sourcecode.servers.Ports;

public class RestServer {

    private final String REST_RESOURCE_DIR = "sourcecode.rest.controller";


    public Server getConfiguredRestServer(){
        ResourceConfig resourceConfig = createResourceConfig();
        ServletHolder servlet = new ServletHolder(new ServletContainer(resourceConfig));
        Server server = new Server(Ports.REST.port);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
        return server;
    }

    private ResourceConfig createResourceConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(REST_RESOURCE_DIR);
        resourceConfig.register(new CorsFilter());
        return resourceConfig;
    }

}
