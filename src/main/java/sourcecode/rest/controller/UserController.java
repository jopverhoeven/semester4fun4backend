package sourcecode.rest.controller;

import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.controller.profile.EditUserGeneralSubmitModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.user.User;
import sourcecode.rest.logic.AuthenticationManager;
import sourcecode.rest.logic.UserManager;
import sourcecode.servers.rest.RestApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

@Path("user")
public class UserController {

    private UserManager userManager = new UserManager();
    private ObjectMapper objectMapper = new ObjectMapper();
    private AuthenticationManager authManager = new AuthenticationManager();

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

    @POST
    @Path("update/general")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfileGeneral(String content) {
        EditUserGeneralSubmitModel submitModel;

        try {
            submitModel = objectMapper.readValue(content, EditUserGeneralSubmitModel.class);
        } catch (IOException e) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        UUID token = submitModel.getToken();
        User user = authManager.loginWithToken(token);

        if (user == null) {
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.TOKEN_INCORRECT));
        }

        userManager.updateUserGeneral(user, submitModel.getFirstname(), submitModel.getLastname());

        return RestApi.getResponse(Response.Status.OK);
    }

//
//    @GET
//    @Path("{name}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUserByName(@PathParam("name") String name) {
//        return Response.ok().build();
//    }
}