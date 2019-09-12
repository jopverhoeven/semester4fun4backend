package sourcecode.rest.dal.repository;

import sourcecode.models.dal.post.CommentDAL;
import sourcecode.rest.dal.data.comment.CommentLocalData;
import sourcecode.rest.dal.interfaces.CommentContext;

import java.util.List;
import java.util.UUID;

public class CommentRepository {

    private static CommentContext commentContext = new CommentLocalData();

    public List<CommentDAL> getCommentByPostId(UUID postId){
        return commentContext.getCommentsByPostId(postId);
    }

    public void addComment(UUID postId, UUID userId, String comment){
        commentContext.addComment(postId, userId, comment);
    }

}
