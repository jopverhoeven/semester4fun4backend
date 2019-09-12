package sourcecode.rest.controller;

import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.other.profile.Profile;
import sourcecode.rest.logic.ProfileManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("profile")
public class ProfileController {

    private ProfileManager profileManager = new ProfileManager();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileByName(@PathParam("name") String name) throws IOException {

        Profile profile = profileManager.getProfileByName(name);

        if (profile == null)
            return Response.status(Response.Status.BAD_REQUEST).build();
        else
            return Response.ok().entity(objectMapper.writeValueAsString(profile)).build();

    }

}
