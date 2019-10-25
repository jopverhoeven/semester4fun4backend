package sourcecode.models.manager.post;

import sourcecode.models.other.error.ApiError;

public class AddPostModel {
    private ApiError apiError;

    public AddPostModel() {}

    public AddPostModel(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
