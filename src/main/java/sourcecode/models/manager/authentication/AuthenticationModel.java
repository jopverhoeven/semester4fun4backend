package sourcecode.models.manager.authentication;

import sourcecode.models.other.error.ApiError;

import java.util.UUID;

public class AuthenticationModel {

    private UUID uuid;
    private ApiError apiError;

    public AuthenticationModel(){
        super();
    }

    public AuthenticationModel(UUID uuid, String errorMessage, String clientMessage){
        setUuid(uuid);

        ApiError apiError = new ApiError(errorMessage, clientMessage);
        setApiError(apiError);
    }

    public AuthenticationModel(UUID uuid, ApiError apiError){
        setUuid(uuid);
        setApiError(apiError);
    }

    public AuthenticationModel(UUID uuid){
        setUuid(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}
