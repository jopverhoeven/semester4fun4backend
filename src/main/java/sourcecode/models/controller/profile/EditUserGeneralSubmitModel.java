package sourcecode.models.controller.profile;

import java.util.UUID;

public class EditUserGeneralSubmitModel {

    private String firstname;
    private String lastname;
    private UUID token;

    public EditUserGeneralSubmitModel(){}

    public EditUserGeneralSubmitModel(String firstname, String lastname, UUID token) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.token = token;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
