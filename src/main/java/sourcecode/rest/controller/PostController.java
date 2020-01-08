package sourcecode.rest.controller;


import org.codehaus.jackson.map.ObjectMapper;
import sourcecode.models.controller.comment.AddCommentSubmitModel;
import sourcecode.models.controller.post.AddPostSubmitModel;
import sourcecode.models.controller.post.LikePostReturnModel;
import sourcecode.models.controller.post.LikePostSubmitModel;
import sourcecode.models.manager.comment.AddCommentModel;
import sourcecode.models.manager.post.AddPostModel;
import sourcecode.models.manager.post.LikePostModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.post.Post;
import sourcecode.models.other.user.User;
import sourcecode.rest.logic.AuthenticationManager;
import sourcecode.rest.logic.CommentManager;
import sourcecode.rest.logic.PostManager;
import sourcecode.rest.logic.UserManager;
import sourcecode.servers.rest.RestApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Path("post")
public class PostController {

    private PostManager postManager = new PostManager();
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserManager userManager = new UserManager();
    private AuthenticationManager authManager = new AuthenticationManager();
    private CommentManager commentManager = new CommentManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}")
    public Response getPost(@PathParam("id") UUID postId) throws IOException {

        Post post = postManager.getPostById(postId);

        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().entity(objectMapper.writeValueAsString(post)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user/{username}")
    public Response getPostByUsername(
            @PathParam("username") String username,
            @QueryParam("sortByDate") boolean sortByDate,
            @QueryParam("from") int from,
            @QueryParam("to") int to) throws IOException {

        User user = userManager.getUserByUsername(username);

        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List<Post> posts = postManager.getPostByUser(user.getUserId());


        return Response.ok().entity(objectMapper.writeValueAsString(posts)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sorted/date")
    public Response getPostsSortedByDate(
            @QueryParam("from") int from,
            @QueryParam("to") int to) throws IOException {

        List<Post> posts = postManager.getPostsSortedByDate();


        return Response.ok().entity(objectMapper.writeValueAsString(posts)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sorted/date/noimage")
    public Response getPostsSortedByDateNoImage() throws IOException {
        List<Post> posts = postManager.getPostsSortedByDateNoImage();
        return Response.ok().entity(objectMapper.writeValueAsString(posts)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("id/{id}/image")
    public Response getPostImage(@PathParam("id") UUID postId) throws IOException {

        Post post = postManager.getPostById(postId);

        if (post == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().entity(objectMapper.writeValueAsString(post.getImageContent())).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("like")
    public Response likePost(String content) {
        LikePostSubmitModel submitModel;

        try {
            submitModel = objectMapper.readValue(content, LikePostSubmitModel.class);
        } catch (IOException e) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        UUID token = submitModel.getToken();
        User user = authManager.loginWithToken(token);

        if (user == null) {
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.TOKEN_INCORRECT));
        }

        LikePostModel likePostModel = postManager.likePost(user, submitModel.getPostId());


        LikePostReturnModel returnModel = new LikePostReturnModel(likePostModel.getLikes(), likePostModel.getApiError());

        if (returnModel.getApiError() != null) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, returnModel.getApiError());
        }

        return RestApi.getResponseWithEntity(Response.Status.OK, returnModel);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    public Response addPost(String content) {
        AddPostSubmitModel submitModel;

        try {
            submitModel = objectMapper.readValue(content, AddPostSubmitModel.class);
        } catch (IOException e) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        UUID token = submitModel.getToken();

        User user = authManager.loginWithToken(token);

        if (user == null) {
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.TOKEN_INCORRECT));
        }

        AddPostModel addPostModel = postManager.addPost(user, submitModel.getImage(), submitModel.getDescription());

        if (addPostModel.getApiError() != null) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, addPostModel.getApiError());
        }

        return RestApi.getResponseWithEntity(Response.Status.OK, addPostModel);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("comment")
    public Response newComment(String content) {
        AddCommentSubmitModel submitModel;

        try {
            submitModel = objectMapper.readValue(content, AddCommentSubmitModel.class);
        } catch (IOException e) {
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, ApiError.getError(ApiErrorMessage.MODEL_INCORRECT));
        }

        UUID token = submitModel.getToken();
        User user = authManager.loginWithToken(token);

        if (user == null) {
            return RestApi.getResponseWithEntity(Response.Status.FORBIDDEN, ApiError.getError(ApiErrorMessage.TOKEN_INCORRECT));
        }

        AddCommentModel addCommentModel = commentManager.addComment(user, submitModel.getPostId(), submitModel.getCommentContent());

        if (addCommentModel.getApiError() != null){
            return RestApi.getResponseWithEntity(Response.Status.BAD_REQUEST, addCommentModel.getApiError());
        }

        return RestApi.getResponseWithEntity(Response.Status.OK, addCommentModel);
    }

}
