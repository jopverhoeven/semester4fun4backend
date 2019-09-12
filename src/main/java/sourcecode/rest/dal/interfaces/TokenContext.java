package sourcecode.rest.dal.interfaces;

import java.util.UUID;

public interface TokenContext {

    UUID addNewUser(UUID userId);
    UUID getUserId(UUID token);

}
