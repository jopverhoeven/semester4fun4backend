package sourcecode.rest.dal.data.authentication;

import sourcecode.rest.dal.interfaces.AuthenticationContext;

import java.util.UUID;

public class AuthenticationRemoteData implements AuthenticationContext {
    @Override
    public boolean login(UUID userId, String password) {
        return false;
    }

    @Override
    public void register(UUID userId, String password) {

    }
}
