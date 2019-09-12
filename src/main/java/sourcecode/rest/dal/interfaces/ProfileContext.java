package sourcecode.rest.dal.interfaces;

import sourcecode.models.dal.profile.ProfileDAL;

public interface ProfileContext {

    ProfileDAL getProfileByUsername(String name);

}
