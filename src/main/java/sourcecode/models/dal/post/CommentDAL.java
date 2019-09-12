package sourcecode.models.dal.post;

import java.util.Date;
import java.util.UUID;

public class CommentDAL {

    private UUID commentId;
    private String commentContent;
    private UUID userId;
    private UUID postId;
    private Date commentDate;

    public CommentDAL(){}

    public CommentDAL(UUID commentId, String commentContent, UUID userId, UUID postId, Date commentDate) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.userId = userId;
        this.postId = postId;
        this.commentDate = commentDate;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
