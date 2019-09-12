package sourcecode.models.controller.post;

import java.util.UUID;

public class LikePostSubmitModel {

    private UUID token;
    private UUID postId;

    public LikePostSubmitModel(){}

    public LikePostSubmitModel(UUID token, UUID postId) {
        this.token = token;
        this.postId = postId;
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
}
