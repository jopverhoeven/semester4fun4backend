package sourcecode.servers;

public enum Ports {

    WEBSOCKET(8092),
    REST(8090);


    public final int port;

    Ports(int port){
        this.port = port;
    }

}
