package sourcecode.models.dal.user;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserDAL {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "UserId")
    private UUID userId;

    @Column(name = "Username")
    private String username;

    @Column(name = "Firstname")
    private String firstname;

    @Column(name = "Lastname")
    private String lastname;

    @Column(name = "ProfilePicture")
    private String profilePicture;

    public UserDAL(){}

    public UserDAL(UUID userId, String firstname, String lastname, String username, String profilePicture){
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
