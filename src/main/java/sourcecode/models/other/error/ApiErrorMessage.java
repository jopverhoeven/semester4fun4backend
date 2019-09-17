package sourcecode.models.other.error;

public enum ApiErrorMessage {

    MODEL_INCORRECT("MODEL_INCORRECT", "Ontvangen model bevat niet de juiste gegevens."),
    TOKEN_INCORRECT("TOKEN_INCORRECT", "De opgegeven token is ongeldig."),
    ID_INCORRECT("ID_INCORRECT", "Object met opgegeven ID bestaat niet."),
    REGISTER_USER_ALREADY_EXISTS("REGISTER_USER_ALREADY_EXISTS", "Deze gebruikersnaam is al in gebruik."),
    REGISTER_PASSWORD_NOT_LONG_ENOUGH("REGISTER_PASSWORD_NOT_LONG_ENOUGH", "Het wachtwoord moet minimaal 8 tekens lang zijn."),
    ;

    public final String errorMessage;
    public final String clientMessage;

    ApiErrorMessage(String errorMessage, String clientMessage){
        this.errorMessage = errorMessage;
        this.clientMessage = clientMessage;
    }

}