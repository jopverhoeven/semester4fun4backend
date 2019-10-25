package sourcecode.rest.dal.interfaces;

import sourcecode.models.other.user.User;

import java.util.List;
import java.util.UUID;

public interface UserContext {

    User getUserById(UUID userId);
    List<User> getUserByName(String name);
    User getUserByUsername(String username);

    User addUser(String username, String firstname, String lastname, String profileImage);

    void updateUserGeneral(UUID userId, String firstname, String lastname);
}
