package sourcecode.rest.dal.interfaces;

import sourcecode.models.dal.post.CommentDAL;

import java.util.List;
import java.util.UUID;

public interface CommentContext {

    List<CommentDAL> getCommentsByPostId(UUID postId);

    void addComment(UUID postId, UUID userId, String comment);
}
