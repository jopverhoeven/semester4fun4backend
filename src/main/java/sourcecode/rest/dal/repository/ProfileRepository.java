package sourcecode.rest.dal.repository;

import sourcecode.models.dal.profile.ProfileDAL;
import sourcecode.rest.dal.data.profile.ProfileLocalData;
import sourcecode.rest.dal.interfaces.ProfileContext;

public class ProfileRepository {

    private static ProfileContext profileContext = new ProfileLocalData();

    public ProfileDAL getProfileByUsername(String username){
        return profileContext.getProfileByUsername(username);
    }

}
