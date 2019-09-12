package sourcecode.rest.dal.interfaces;

import sourcecode.models.other.user.User;

import java.util.List;
import java.util.UUID;

public interface UserContext {

    User getUserById(UUID userId);
    List<User> getUserByName(String name);
    User getUserByUsername(String username);

}
