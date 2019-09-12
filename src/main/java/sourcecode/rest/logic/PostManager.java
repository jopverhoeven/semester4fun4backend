package sourcecode.rest.logic;

import sourcecode.models.dal.post.PostDAL;
import sourcecode.models.manager.post.LikePostModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.post.Comment;
import sourcecode.models.other.post.Post;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.PostRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PostManager {

    private PostRepository postRepository = new PostRepository();
    private static UserManager userManager = new UserManager();
    private static CommentManager commentManager = new CommentManager();

    public Post getPostById(UUID postId){
        PostDAL postDAL = postRepository.getPostById(postId);

        return convertPostDALToPost(postDAL);
    }

    public List<Post> getPostByUser(UUID userId){

        List<PostDAL> postDALS = postRepository.getPostsFromUser(userId);

        return convertMultiplePostDALtoPost(postDALS);

    }

    public List<Post> getPosts(){
        return convertMultiplePostDALtoPost(postRepository.getPosts());
    }

    public List<Post> getPostsSortedByDate(){
        List<Post> allPosts = getPosts();
        Collections.sort(allPosts);
        Collections.reverse(allPosts);

        return allPosts;
    }

    public LikePostModel likePost(User user, UUID postId){
        Post post = this.getPostById(postId);

        if (post == null){
            return new LikePostModel(null, ApiError.getError(ApiErrorMessage.ID_INCORRECT));
        }

        if (!postRepository.hasLiked(postId, user.getUserId())){
            postRepository.addLike(postId, user.getUserId());
        }else {
            postRepository.removeLike(postId, user.getUserId());
        }

        List<UUID> likeIds = postRepository.getLikes(postId);
        List<User> likeUsers = userManager.convertUUIDsToUser(likeIds);

        return new LikePostModel(likeUsers, null);
    }



    private Post convertPostDALToPost(PostDAL postDAL){

        if (postDAL == null)
            return null;

        User postUser = userManager.getUser(postDAL.getUserId());

        if (postUser == null)
            return null;

        List<Comment> postComments = commentManager.getCommentsByPostId(postDAL.getPostId());

        List<UUID> likes = postDAL.getLikes();
        List<User> userLikes = new ArrayList<>();
        for(UUID liker : likes){
            userLikes.add(userManager.getUser(liker));
        }


        Post returnPost = new Post(
                postDAL.getPostId(),
                postDAL.getPostDescription(),
                postDAL.getImageContent(),
                postUser,
                postComments,
                userLikes,
                postDAL.getPostDate()
        );

        return returnPost;
    }

    private List<Post> convertMultiplePostDALtoPost(List<PostDAL> postDALS){

        List<Post> returnPosts = new ArrayList<>();

        for (PostDAL postDAL : postDALS){
            Post post = convertPostDALToPost(postDAL);
            returnPosts.add(post);
        }

        return returnPosts;
    }
}
