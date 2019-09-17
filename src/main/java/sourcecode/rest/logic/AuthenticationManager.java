package sourcecode.rest.logic;

import sourcecode.models.manager.authentication.AuthenticationModel;
import sourcecode.models.manager.authentication.RegisterModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.authentication.AuthenticationRepository;
import sourcecode.rest.dal.repository.authentication.TokenRepository;

import java.util.UUID;

public class AuthenticationManager {

    private static AuthenticationRepository authRepo = new AuthenticationRepository();
    private static TokenRepository tokenRepository = new TokenRepository();
    private UserManager userManager = new UserManager();
    private ProfileManager profileManager = new ProfileManager();

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

    public RegisterModel register(String username, String firstname, String lastname, String profileImage, String password){
        RegisterModel registerModel = new RegisterModel();

        User inUse = userManager.getUserByUsername(username);

        if (inUse != null){
            registerModel.setApiError(ApiError.getError(ApiErrorMessage.REGISTER_USER_ALREADY_EXISTS));
            return registerModel;
        }

        if (password.length() < 8){
            registerModel.setApiError(ApiError.getError(ApiErrorMessage.REGISTER_PASSWORD_NOT_LONG_ENOUGH));
            return registerModel;
        }

        User user = userManager.addUser(username, firstname, lastname, profileImage);
        authRepo.register(user.getUserId(), password);
        profileManager.addProfile(user.getUserId());

        return registerModel;
    }


}
