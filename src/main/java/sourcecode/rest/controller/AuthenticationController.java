package sourcecode.rest.controller;

import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.controller.authentication.AuthenticationReturnModel;
import sourcecode.models.controller.authentication.AuthenticationSubmitModel;
import sourcecode.models.controller.authentication.RegisterReturnModel;
import sourcecode.models.controller.authentication.RegisterSubmitModel;
import sourcecode.models.manager.authentication.AuthenticationModel;
import sourcecode.models.manager.authentication.RegisterModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.user.User;
import sourcecode.rest.logic.AuthenticationManager;
import sourcecode.servers.rest.RestApi;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @POST
    @Path("token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginWithToken(String content) {

        UUID token;

        try {
            token = UUID.fromString(content);
        }catch(IllegalArgumentException e){
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        User user = authManager.loginWithToken(token);

        if (user == null){
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.TOKEN_INCORRECT));
        }

        return RestApi.getResponseWithEntity(Response.Status.OK, user);
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(String content){
        RegisterSubmitModel submitModel;

        try {
            submitModel = objectMapper.readValue(content, RegisterSubmitModel.class);
        } catch (IOException e) {
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        RegisterModel registerModel = authManager.register(submitModel.getUsername(), submitModel.getFirstname(), submitModel.getLastname(), submitModel.getProfilePicture(), submitModel.getPassword());

        if (registerModel.getApiError() != null){
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, registerModel.getApiError());
        }

        RegisterReturnModel returnModel = new RegisterReturnModel(registerModel.getApiError());

        return RestApi.getResponseWithEntity(Response.Status.OK, returnModel);
    }

}
