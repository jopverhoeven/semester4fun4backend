package sourcecode.rest.dal.repository;

import sourcecode.models.other.user.User;
import sourcecode.rest.dal.data.user.UserLocalData;
import sourcecode.rest.dal.interfaces.UserContext;

import java.util.List;
import java.util.UUID;

public class UserRepository {

    private static UserContext userContext = new UserLocalData();

    public UserRepository() {}

    public UserRepository(UserContext context) {
        UserRepository.userContext = context;
    }

    public User getUserById(UUID id){
        return userContext.getUserById(id);
    }

    public List<User> getUserByName(String name){
        return userContext.getUserByName(name);
    }

    public User getUserByUsername(String username) {
        return userContext.getUserByUsername(username);
    }

    public User addUser(String username, String firstname, String lastname, String profileImage) {
        return userContext.addUser(username, firstname, lastname, profileImage);
    }

    public void updateUserGeneral(UUID userId, String firstname, String lastname) {
        userContext.updateUserGeneral(userId, firstname, lastname);
    }
}
