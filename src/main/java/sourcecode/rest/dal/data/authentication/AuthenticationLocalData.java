package sourcecode.rest.dal.data.authentication;

import sourcecode.rest.dal.interfaces.AuthenticationContext;

import java.util.HashMap;
import java.util.UUID;

public class AuthenticationLocalData implements AuthenticationContext {

    private static HashMap<UUID, String> userPassword = new HashMap<>();

    public AuthenticationLocalData(){
        createPasswords();
    }

    private void createPasswords() {

        userPassword.put(UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"), "testpassword");
        userPassword.put(UUID.fromString("e7019164-6b27-47ba-b162-cf0d2a07d186"), "test123");
        userPassword.put(UUID.fromString("54bf8482-1a58-4a99-84d5-3c2278147ffa"), "test123");
        userPassword.put(UUID.fromString("64198b11-17cc-438c-b1f7-23637330d10a"), "test123");

    }

    @Override
    public boolean login(UUID userId, String password) {
        if (userPassword.containsKey(userId)){
            String storedPass = userPassword.get(userId);

            if (storedPass.equals(password))
                return true;
        }

        return false;
    }

    @Override
    public void register(UUID userId, String password) {
        userPassword.put(userId, password);
    }
}
