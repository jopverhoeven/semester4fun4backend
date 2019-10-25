package sourcecode.models.manager.post;

import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.post.Post;

public class AddPostModel {
    private Post post;
    private ApiError apiError;

    public AddPostModel() {}

    public AddPostModel(Post post, ApiError apiError) {
        this.post = post;
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
