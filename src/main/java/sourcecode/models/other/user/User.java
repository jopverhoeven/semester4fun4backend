package sourcecode.models.other.user;

import java.util.UUID;

public class User {
    private UUID userId;
    private String username;
    private String firstname;
    private String lastname;
    private String profilePicture;

    public User(){}

    public User(UUID userId, String firstname, String lastname, String username, String profilePicture){
        setProfilePicture(profilePicture);
        setUserId(userId);
        setUsername(username);
        setFirstname(firstname);
        setLastname(lastname);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
}
