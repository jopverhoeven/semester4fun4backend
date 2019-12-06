package sourcecode.servers.rest;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class RestServer {

    private final String REST_RESOURCE_DIR = "sourcecode.rest.controller";


    public Server getConfiguredRestServer(){
        ResourceConfig resourceConfig = createResourceConfig();
        ServletHolder servlet = new ServletHolder(new ServletContainer(resourceConfig));
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));

//        try {
//            configureHTTPS(server, 8090);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
        return server;
    }

    private void configureHTTPS(Server server, int securePort) throws Exception {

        // HTTP Configuration
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(securePort);
        httpConfig.setOutputBufferSize(32768);
        httpConfig.setRequestHeaderSize(8192);
        httpConfig.setResponseHeaderSize(8192);
        httpConfig.setSendServerVersion(true);
        httpConfig.setSendDateHeader(false);

        URL keystorePath = RestServer.class.getResource("/keystore");
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(keystorePath.toExternalForm());
        sslContextFactory.setKeyStorePassword("se4fun4");
        sslContextFactory.setTrustStorePath(keystorePath.toString());
        sslContextFactory.setTrustStorePassword("se4fun4");

        // SSL HTTP Configuration
        HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
        httpsConfig.addCustomizer(new SecureRequestCustomizer());

        // SSL Connector
        ServerConnector sslConnector = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                new HttpConnectionFactory(httpsConfig));
        sslConnector.setPort(securePort);
        server.addConnector(sslConnector);

//        HttpConfiguration https = new HttpConfiguration();
//        https.addCustomizer(new SecureRequestCustomizer());
//        SslContextFactory sslContextFactory = new SslContextFactory();
//        sslContextFactory.setKeyStorePath(RestServer.class.getResource(
//                "/keystore.jks").toExternalForm());
//        sslContextFactory.setKeyStorePassword("semester4fun4backend");
//        sslContextFactory.setKeyManagerPassword("semester4fun4backend");
//        ServerConnector sslConnector = new ServerConnector(server,
//                new SslConnectionFactory(sslContextFactory, "http/1.1"),
//                new HttpConnectionFactory(https));
//        sslConnector.setPort(8090);
//        server.setConnectors(new Connector[] { sslConnector });
    }

    private ResourceConfig createResourceConfig() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(REST_RESOURCE_DIR);
        resourceConfig.register(new CorsFilter());
        return resourceConfig;
    }

    private Resource findKeyStore() throws URISyntaxException, MalformedURLException
    {
        ClassLoader cl = getClass().getClassLoader();
        String keystoreResource = "keystore.jks";
        URL f = cl.getResource(keystoreResource);
        if (f == null)
        {
            throw new RuntimeException("Unable to find " + keystoreResource);
        }

        return Resource.newResource(f.toURI());
    }

}
