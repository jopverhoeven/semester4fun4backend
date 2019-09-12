package sourcecode;

import org.eclipse.jetty.server.Server;
import sourcecode.servers.rest.RestServer;
import sourcecode.servers.websocket.WebsocketServer;

public class Application {


    public static void main(String[] args) {
        Server restServer = new RestServer().getConfiguredRestServer();
        Server websocketServer = new WebsocketServer().getConfiguredWebsocketServer();

        try {
            websocketServer.start();
            restServer.start();
            restServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            restServer.destroy();
            websocketServer.destroy();
        }

    }


}
