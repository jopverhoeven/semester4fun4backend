package sourcecode.models.dal.authentication;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "AUTHENTICATION")
public class AuthenticationDAL {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "USER_ID", length = 36)
    private UUID userId;

    @Column(name = "PASSWORD")
    private String password;

    public AuthenticationDAL() {}

    public AuthenticationDAL(UUID userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
