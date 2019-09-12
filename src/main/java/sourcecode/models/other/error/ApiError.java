package sourcecode.models.other.error;

public class ApiError {
    private String errorMessage;
    private String clientMessage;

    public ApiError(){}

    public static ApiError getError(ApiErrorMessage errorMessage){
        return new ApiError(errorMessage);
    }

    public ApiError(String errorMessage, String clientMessage) {
        this.errorMessage = errorMessage;
        this.clientMessage = clientMessage;
    }

    public ApiError(ApiErrorMessage errorMessage){
        setErrorMessage(errorMessage.errorMessage);
        setClientMessage(errorMessage.clientMessage);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }
}
