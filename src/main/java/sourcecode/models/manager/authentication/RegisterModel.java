package sourcecode.models.manager.authentication;

import sourcecode.models.other.error.ApiError;

public class RegisterModel {

    private ApiError apiError;

    public RegisterModel(){}

    public RegisterModel(ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
