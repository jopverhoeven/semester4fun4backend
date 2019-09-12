package sourcecode.models.dal.profile;

import sourcecode.models.other.profile.Privacy;

import java.util.UUID;

public class ProfileDAL {

    private UUID profileId;
    private UUID userId;
    private Privacy profilePrivacy;

    public ProfileDAL(){}

    public ProfileDAL(UUID profileId, UUID userId, Privacy privacy){
        setProfileId(profileId);
        setUserId(userId);
        setProfilePrivacy(privacy);
    }


    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Privacy getProfilePrivacy() {
        return profilePrivacy;
    }

    public void setProfilePrivacy(Privacy profilePrivacy) {
        this.profilePrivacy = profilePrivacy;
    }
}
