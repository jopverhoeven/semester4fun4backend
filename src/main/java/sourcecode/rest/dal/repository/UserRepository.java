package sourcecode.rest.dal.repository;

import sourcecode.models.other.user.User;
import sourcecode.rest.dal.data.user.UserLocalData;
import sourcecode.rest.dal.interfaces.UserContext;

import java.util.List;
import java.util.UUID;

public class UserRepository {

    private static UserContext userContext = new UserLocalData();

    public User getUserById(UUID id){
        return userContext.getUserById(id);
    }

    public List<User> getUserByName(String name){
        return userContext.getUserByName(name);
    }

    public User getUserByUsername(String username) {
        return userContext.getUserByUsername(username);
    }
}
