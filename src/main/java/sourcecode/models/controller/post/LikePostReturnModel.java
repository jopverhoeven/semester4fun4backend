package sourcecode.models.controller.post;

import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.user.User;

import java.util.List;

public class LikePostReturnModel {

    private List<User> likes;
    private ApiError apiError;

    public LikePostReturnModel(){}

    public LikePostReturnModel(List<User> likes, ApiError apiError) {
        this.likes = likes;
        this.apiError = apiError;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
