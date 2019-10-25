package User;

import org.junit.Assert;
import org.junit.Test;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.data.user.UserLocalData;
import sourcecode.rest.dal.repository.UserRepository;
import sourcecode.rest.logic.UserManager;

import java.util.UUID;

public class UserTest {

    private UserManager userManager;
    private UserRepository userRepository;

    @Test
    public void createNewUser(){
        this.userRepository = new UserRepository(new UserLocalData());
        this.userManager = new UserManager(this.userRepository);

        String username = "TestUser01";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        UUID userId = user.getUserId();

        Assert.assertTrue(userManager.getUser(userId) != null);
    }

}
