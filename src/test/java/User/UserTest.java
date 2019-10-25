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

        String username = "CreateUser01";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        UUID userId = user.getUserId();

        Assert.assertTrue(userManager.getUser(userId) != null);
    }

    @Test
    public void updateUserSuccessfully() {
        this.userRepository = new UserRepository(new UserLocalData());
        this.userManager = new UserManager(this.userRepository);

        String username = "UpdateUser01";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        UUID userId = user.getUserId();

        String newFirstname = "UpdatedTest";
        String newLastname = "UpdatedUser";

        userManager.updateUserGeneral(user, newFirstname, newLastname);

        Assert.assertTrue(userManager.getUser(userId).getFirstname().equals(newFirstname));
        Assert.assertTrue(userManager.getUser(userId).getLastname().equals(newLastname));
    }

    @Test
    public void updateUserIncorrectNewFirstname() {
        this.userRepository = new UserRepository(new UserLocalData());
        this.userManager = new UserManager(this.userRepository);

        String username = "UpdateUser01";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        UUID userId = user.getUserId();

        String newFirstname = "";
        String newLastname = "UpdatedUser";

        userManager.updateUserGeneral(user, newFirstname, newLastname);

        Assert.assertTrue(userManager.getUser(userId).getFirstname().equals(firstname));
        Assert.assertTrue(userManager.getUser(userId).getLastname().equals(lastname));
    }

    @Test
    public void updateUserIncorrectNewLastname() {
        this.userRepository = new UserRepository(new UserLocalData());
        this.userManager = new UserManager(this.userRepository);

        String username = "UpdateUser01";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        UUID userId = user.getUserId();

        String newFirstname = "UpdatedTest";
        String newLastname = "";

        userManager.updateUserGeneral(user, newFirstname, newLastname);

        Assert.assertTrue(userManager.getUser(userId).getFirstname().equals(firstname));
        Assert.assertTrue(userManager.getUser(userId).getLastname().equals(lastname));
    }

    @Test
    public void getUserByUserId() {
        this.userRepository = new UserRepository(new UserLocalData());
        this.userManager = new UserManager(this.userRepository);

        String username = "GetUser01";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        Assert.assertTrue(userManager.getUser(user.getUserId()) == user);
    }

    @Test
    public void getUserByUsername() {
        this.userRepository = new UserRepository(new UserLocalData());
        this.userManager = new UserManager(this.userRepository);

        String username = "GetUser02";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        Assert.assertTrue(userManager.getUserByUsername(user.getUsername()) == user);
    }

}
