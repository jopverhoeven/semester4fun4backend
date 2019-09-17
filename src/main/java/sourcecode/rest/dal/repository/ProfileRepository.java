package sourcecode.rest.dal.repository;

import sourcecode.models.dal.profile.ProfileDAL;
import sourcecode.rest.dal.data.profile.ProfileLocalData;
import sourcecode.rest.dal.interfaces.ProfileContext;

import java.util.UUID;

public class ProfileRepository {

    private static ProfileContext profileContext = new ProfileLocalData();

    public ProfileDAL getProfileByUsername(String username){
        return profileContext.getProfileByUsername(username);
    }

    public void addProfile(UUID userId) {
        profileContext.addProfile(userId);
    }
}
