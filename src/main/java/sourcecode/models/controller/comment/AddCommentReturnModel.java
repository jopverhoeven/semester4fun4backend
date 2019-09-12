package sourcecode.models.controller.comment;

import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.post.Comment;

import java.util.List;

public class AddCommentReturnModel {

    private List<Comment> comments;
    private ApiError apiError;

    public AddCommentReturnModel(){}

    public AddCommentReturnModel(List<Comment> comments, ApiError apiError) {
        this.comments = comments;
        this.apiError = apiError;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
