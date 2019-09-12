package sourcecode.rest.dal.repository.authentication;

import sourcecode.rest.dal.data.authentication.TokenLocalData;
import sourcecode.rest.dal.interfaces.TokenContext;

import java.util.UUID;

public class TokenRepository {

    private static TokenContext tokenContext = new TokenLocalData();

    public UUID addNewUser(UUID userId){
        return tokenContext.addNewUser(userId);
    }

    public UUID getUserId(UUID token){
        return tokenContext.getUserId(token);
    }

}
