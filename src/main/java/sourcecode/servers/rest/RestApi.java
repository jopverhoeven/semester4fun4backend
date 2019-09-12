package sourcecode.servers.rest;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;

public class RestApi {

    public static Response getResponseWithEntity(Response.Status status, Object object){
        try {
            return Response.status(status).entity(new ObjectMapper().writeValueAsString(object)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
