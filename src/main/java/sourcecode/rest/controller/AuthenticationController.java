package sourcecode.rest.controller;

import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.controller.authentication.AuthenticationReturnModel;
import sourcecode.models.controller.authentication.AuthenticationSubmitModel;
import sourcecode.models.manager.authentication.AuthenticationModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.user.User;
import sourcecode.rest.logic.AuthenticationManager;
import sourcecode.servers.rest.RestApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

@Path("auth")
public class AuthenticationController {

    private AuthenticationManager authManager = new AuthenticationManager();
    private ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String content) throws IOException {
        AuthenticationSubmitModel submitModel = objectMapper.readValue(content, AuthenticationSubmitModel.class);

        AuthenticationModel authModel = authManager.login(submitModel.getUsername(), submitModel.getPassword());

        AuthenticationReturnModel returnModel = new AuthenticationReturnModel(authModel.getUuid(), authModel.getApiError());

        if (authModel.getUuid() == null){
            return Response.status(Response.Status.FORBIDDEN).entity(objectMapper.writeValueAsString(returnModel.getApiError())).build();
        }

        return Response.ok().entity(objectMapper.writeValueAsString(returnModel)).build();
    }

    @GET
    @Path("token/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginWithToken(@PathParam("token") UUID token) throws IOException {

        User user = authManager.loginWithToken(token);

        if (user == null){
            ApiError apiError = new ApiError("TOKEN_INCORRECT", "Ongeldige token.");
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, apiError);
        }

        return RestApi.getResponseWithEntity(Response.Status.OK, user);
    }

    public Response register(){
        return null;
    }

}
