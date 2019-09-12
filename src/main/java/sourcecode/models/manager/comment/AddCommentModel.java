package sourcecode.models.manager.comment;

import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.post.Comment;

import java.util.List;

public class AddCommentModel {

    private List<Comment> comments;
    private ApiError apiError;

    public AddCommentModel(){}

    public AddCommentModel(List<Comment> comments, ApiError apiError) {
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
