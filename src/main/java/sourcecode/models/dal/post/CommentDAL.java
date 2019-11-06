package sourcecode.models.dal.post;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comment")
public class CommentDAL {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "COMMENT_ID", length = 36)
    private UUID commentId;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Type(type = "uuid-char")
    @Column(name = "USER_ID", length = 36)
    private UUID userId;

    @Type(type = "uuid-char")
    @Column(name = "POST_ID", length = 36)
    private UUID postId;

    @Column(name = "COMMENT_DATE")
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
