package sourcecode.rest.dal.repository;

import sourcecode.models.dal.post.PostDAL;
import sourcecode.models.dal.post.PostNoImageDAL;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.data.post.PostRemoteData;
import sourcecode.rest.dal.interfaces.PostContext;

import java.util.List;
import java.util.UUID;

public class PostRepository {

    private static PostContext postContext = new PostRemoteData();

    public PostRepository() {}
    public PostRepository(PostContext postContext) {
        PostRepository.postContext = postContext;
    }

    public PostDAL getPostById(UUID postId){
        return postContext.getPostById(postId);
    }

    public PostNoImageDAL getPostByIdNoImage(UUID postId) {
        return postContext.getPostByIdNoImage(postId);
    }

    public List<PostDAL> getPostsFromUser(UUID userId){ return postContext.getPostFromUser(userId, 0, 0); }
    public List<PostDAL> getPosts(){return postContext.getPosts(); }

    public List<PostNoImageDAL> getPostsNoImage(){ return postContext.getPostsNoImage(); }

    public List<UUID> getLikes(UUID postId){
        return postContext.getLikes(postId);
    }

    public boolean hasLiked(UUID postId, UUID userId){
        return postContext.hasLiked(postId, userId);
    }

    public void addLike(UUID postId, UUID userId) {
        postContext.addLike(postId, userId);
    }

    public void removeLike(UUID postId, UUID userId) {
        postContext.removeLike(postId, userId);
    }

    public PostDAL addPost(User user, String image, String description) {
        return postContext.addPost(user, image, description);
    }
}
