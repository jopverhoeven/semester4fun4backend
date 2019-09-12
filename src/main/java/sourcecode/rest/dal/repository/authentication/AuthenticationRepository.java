package sourcecode.rest.dal.repository.authentication;

import sourcecode.rest.dal.data.authentication.AuthenticationLocalData;
import sourcecode.rest.dal.interfaces.AuthenticationContext;

import java.util.UUID;

public class AuthenticationRepository {

    private static AuthenticationContext authContext = new AuthenticationLocalData();

    public boolean login(UUID userId, String password){
        return authContext.login(userId, password);
    }

}
