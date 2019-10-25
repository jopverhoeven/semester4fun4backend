package sourcecode.servers.rest;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class RestApi {

    public static Response getResponseWithEntity(Response.Status status, Object object) {
        try {
            return Response.status(status).entity(new ObjectMapper().writeValueAsString(object)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Response getResponse(Response.Status status) {
        return Response.status(status).build();
    }

    public static Response getResponseWithCookie(Response.Status status, RestCookie cookieKey, String cookieData) {
        Cookie cookie = new Cookie(cookieKey.cookieKey, cookieData);

        return Response.status(status)
                .cookie(new NewCookie(cookie))
                .build();
    }

    public static Response getResponseWithEntityAndCookie(Response.Status status, Object object, RestCookie cookieKey, String cookieData) {
        Cookie cookie = new Cookie(cookieKey.cookieKey, cookieData);

        try {
            return Response.status(status)
                    .entity(new ObjectMapper().writeValueAsString(object))
                    .cookie(new NewCookie(cookie))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
