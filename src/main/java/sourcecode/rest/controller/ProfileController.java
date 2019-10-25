package sourcecode.rest.controller;

import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.controller.profile.FollowProfileSubmitModel;
import sourcecode.models.manager.profile.FollowProfileModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.profile.Profile;
import sourcecode.models.other.user.User;
import sourcecode.rest.logic.AuthenticationManager;
import sourcecode.rest.logic.ProfileManager;
import sourcecode.servers.rest.RestApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

@Path("profile")
public class ProfileController {

    private ProfileManager profileManager = new ProfileManager();
    private AuthenticationManager authManager = new AuthenticationManager();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileByName(@PathParam("name") String name) throws IOException {

        Profile profile = profileManager.getProfileByName(name);

        if (profile == null)
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, ApiError.getError(ApiErrorMessage.USER_NOT_FOUND));

        return RestApi.getResponseWithEntity(Response.Status.OK, profile);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("follow")
    public Response followProfile(String content) {
        FollowProfileSubmitModel submitModel;

        try {
            submitModel = objectMapper.readValue(content, FollowProfileSubmitModel.class);
        } catch (IOException e) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        UUID token = submitModel.getToken();

        User user = authManager.loginWithToken(token);

        if (user == null) {
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.TOKEN_INCORRECT));
        }

        FollowProfileModel followProfileModel = profileManager.followProfile(user, submitModel.getProfileId());

        return RestApi.getResponseWithEntity(Response.Status.OK, followProfileModel);
    }
}
