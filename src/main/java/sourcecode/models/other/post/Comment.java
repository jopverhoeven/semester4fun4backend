package sourcecode.models.other.post;

import sourcecode.models.other.user.User;

import java.util.Date;
import java.util.UUID;

public class Comment implements Comparable<Comment>{

    private UUID commentId;
    private String commentContent;
    private User commentUser;
    private Date commentDate;

    public Comment(){}

    public Comment(UUID commentId, String commentContent, User commentUser, Date commentDate) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentUser = commentUser;
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

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public int compareTo(Comment o) {
        if (o.getCommentDate() == null || getCommentDate() == null)
            return 0;

        return getCommentDate().compareTo(o.getCommentDate());
    }
}
