package sourcecode.rest.dal.data.authentication;

import sourcecode.rest.dal.interfaces.TokenContext;

import java.util.HashMap;
import java.util.UUID;

public class TokenLocalData implements TokenContext {

    private static HashMap<UUID, UUID> userTokens = new HashMap<>();

    @Override
    public UUID addNewUser(UUID userId) {
        UUID token = UUID.randomUUID();

        userTokens.put(token, userId);

        return token;
    }

    @Override
    public UUID getUserId(UUID token) {
        if (userTokens.containsKey(token)){
            return userTokens.get(token);
        }

        return null;
    }
}
