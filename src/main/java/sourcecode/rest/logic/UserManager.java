package sourcecode.rest.logic;

import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {

    private UserRepository userRepository = new UserRepository();

    public UserManager() {}

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(UUID uuid){
        return userRepository.getUserById(uuid);
    }

    public List<User> getUsersByName(String name){
        return userRepository.getUserByName(name);
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }

    public List<User> convertUUIDsToUser(List<UUID> uuidList){
        List<User> users = new ArrayList<>();

        for(UUID userId: uuidList){
            users.add(getUser(userId));
        }

        return users;
    }

    public User addUser(String username, String firstname, String lastname, String profileImage) {
        return userRepository.addUser(username, firstname, lastname, profileImage);
    }

    public void updateUserGeneral(User user, String firstname, String lastname) {
        userRepository.updateUserGeneral(user.getUserId(), firstname, lastname);
    }
}
