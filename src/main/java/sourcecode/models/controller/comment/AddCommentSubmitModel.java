package sourcecode.models.controller.comment;

import java.util.UUID;

public class AddCommentSubmitModel {

    private UUID token;
    private UUID postId;
    private String commentContent;

    public AddCommentSubmitModel(){}

    public AddCommentSubmitModel(UUID token, UUID postId, String commentContent) {
        this.token = token;
        this.postId = postId;
        this.commentContent = commentContent;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
