package sourcecode.servers.websocket;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import sourcecode.servers.Ports;

import javax.servlet.ServletException;
import javax.websocket.server.ServerContainer;

public class WebsocketServer {

    public Server getConfiguredWebsocketServer(){
        Server server = new Server(Ports.WEBSOCKET.port);
        ServletContextHandler bidContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        bidContextHandler.setContextPath("/bid");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{bidContextHandler});
        server.setHandler(handlers);

        ServerContainer container = null;
        try {
            container = WebSocketServerContainerInitializer.configureContext(bidContextHandler);
        } catch (ServletException e) {

        }


        return server;
    }

}
