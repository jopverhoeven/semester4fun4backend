package sourcecode.rest.dal.interfaces;

import java.util.UUID;

public interface AuthenticationContext {

    boolean login(UUID userId, String password);

}
