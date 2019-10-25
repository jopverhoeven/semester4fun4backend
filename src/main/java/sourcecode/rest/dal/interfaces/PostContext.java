package sourcecode.rest.dal.interfaces;

import sourcecode.models.dal.post.PostDAL;
import sourcecode.models.other.user.User;

import java.util.List;
import java.util.UUID;

public interface PostContext {

    PostDAL getPostById(UUID postId);

    List<PostDAL> getPostFromUser(UUID userId, int from, int to);

    List<PostDAL> getPosts();

    boolean hasLiked(UUID postId, UUID userId);

    void addLike(UUID postId, UUID userId);

    void removeLike(UUID postId, UUID userId);

    List<UUID> getLikes(UUID postId);

    void addPost(User user, String image, String description);
}
