package sourcecode.rest.dal.repository.authentication;

import sourcecode.rest.dal.data.authentication.AuthenticationRemoteData;
import sourcecode.rest.dal.interfaces.AuthenticationContext;

import java.util.UUID;

public class AuthenticationRepository {

    private static AuthenticationContext authContext = new AuthenticationRemoteData();

    public boolean login(UUID userId, String password){
        return authContext.login(userId, password);
    }

    public void register(UUID userId, String password) {
        authContext.register(userId, password);
    }
}
