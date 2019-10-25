package sourcecode.models.controller.post;

import sourcecode.models.other.error.ApiError;

public class AddPostReturnModel {
    private ApiError apiError;

    public AddPostReturnModel() {}

    public AddPostReturnModel(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
