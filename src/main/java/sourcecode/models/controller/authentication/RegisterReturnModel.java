package sourcecode.models.controller.authentication;

import sourcecode.models.other.error.ApiError;

public class RegisterReturnModel {

    private ApiError apiError;

    public RegisterReturnModel(){}

    public RegisterReturnModel(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
