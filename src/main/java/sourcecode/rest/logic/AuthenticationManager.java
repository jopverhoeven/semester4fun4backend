package sourcecode.rest.logic;

import sourcecode.models.manager.authentication.AuthenticationModel;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.authentication.AuthenticationRepository;
import sourcecode.rest.dal.repository.authentication.TokenRepository;

import java.util.UUID;

public class AuthenticationManager {

    private static AuthenticationRepository authRepo = new AuthenticationRepository();
    private static TokenRepository tokenRepository = new TokenRepository();
    private UserManager userManager = new UserManager();

    public AuthenticationModel login(String username, String password){

        User user = userManager.getUserByUsername(username);

        if (user == null){
            return new AuthenticationModel(null, "USER_NOT_FOUND", "Deze gebruiker bestaat niet.");
        }

        UUID userId = user.getUserId();

        boolean loginSuccessful = authRepo.login(userId, password);

        if (loginSuccessful == false){
            return new AuthenticationModel(null, "PASS_INCORRECT", "Het wachtwoord is onjuist.");
        }

        UUID loginToken = tokenRepository.addNewUser(userId);

        return new AuthenticationModel(loginToken);

    }

    public User loginWithToken(UUID token){
        UUID userId = tokenRepository.getUserId(token);
        if (userId == null)
            return null;

        User user = userManager.getUser(userId);
        if (user == null)
            return null;

        return user;
    }


}
