package sourcecode.models.controller.authentication;

public class RegisterSubmitModel {

    private String username;
    private String firstname;
    private String lastname;
    private String profilePicture;
    private String password;

    public RegisterSubmitModel(){}

    public RegisterSubmitModel(String username, String firstname, String lastname, String profilePicture, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePicture = profilePicture;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
