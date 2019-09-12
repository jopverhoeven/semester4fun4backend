package sourcecode.models.controller.authentication;

import sourcecode.models.other.error.ApiError;

import java.util.UUID;

public class AuthenticationReturnModel {

    private UUID token;
    private ApiError apiError;

    public AuthenticationReturnModel(){}

    public AuthenticationReturnModel(UUID token, ApiError apiError) {
        this.token = token;
        this.apiError = apiError;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
