package sourcecode.rest.controller;

import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.other.user.User;
import sourcecode.rest.logic.UserManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

@Path("user")
public class UserController {

    private UserManager userManager = new UserManager();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") UUID id) throws IOException {
        User user = userManager.getUser(id);

        if (user == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        else {
            return Response.ok().entity(objectMapper.writeValueAsString(user)).build();
        }
    }

//
//    @GET
//    @Path("{name}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserByName(@PathParam("name") String name) {
//        return Response.ok().build();
//    }
}