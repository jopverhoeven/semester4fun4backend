package sourcecode.rest.dal.interfaces;

import sourcecode.models.dal.profile.ProfileDAL;

import java.util.List;
import java.util.UUID;

public interface ProfileContext {

    ProfileDAL getProfileByUsername(String name);
    ProfileDAL getProfileById(UUID profileId);

    void addProfile(UUID userId);

    List<UUID> getFollowingProfiles(UUID profileId);

    boolean followProfile(UUID userId, ProfileDAL profileId);
}
