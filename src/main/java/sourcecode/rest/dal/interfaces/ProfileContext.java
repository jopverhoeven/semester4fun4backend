package sourcecode.rest.dal.interfaces;

import sourcecode.models.dal.profile.ProfileDAL;

import java.util.UUID;

public interface ProfileContext {

    ProfileDAL getProfileByUsername(String name);

    void addProfile(UUID userId);
}
