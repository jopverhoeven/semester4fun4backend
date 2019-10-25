package sourcecode.models.dal.profile;

import sourcecode.models.other.profile.Privacy;

import java.util.List;
import java.util.UUID;

public class ProfileDAL {

    private UUID profileId;
    private UUID userId;
    private Privacy profilePrivacy;
    private String profileStatus;
    private List<UUID> followerIds;

    public ProfileDAL(){}

    public ProfileDAL(UUID profileId, UUID userId, Privacy privacy, String profileStatus, List<UUID> followerIds){
        this.profileStatus = profileStatus;
        this.followerIds = followerIds;
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

    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

    public List<UUID> getFollowerIds() {
        return followerIds;
    }

    public void setFollowerIds(List<UUID> followerIds) {
        this.followerIds = followerIds;
    }
}
