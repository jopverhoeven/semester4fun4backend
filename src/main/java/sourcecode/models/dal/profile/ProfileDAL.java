package sourcecode.models.dal.profile;

import org.hibernate.annotations.Type;
import sourcecode.models.other.profile.Privacy;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "PROFILE")
public class ProfileDAL {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "PROFILE_ID", length = 36)
    private UUID profileId;

    @Type(type = "uuid-char")
    @Column(name="USER_ID", length = 36)
    private UUID userId;

    @Transient
    private Privacy profilePrivacy = new Privacy(true);

    @Column(name = "PROFILE_STATUS")
    private String profileStatus;

    @Transient
    private List<UUID> followerIds;

    public ProfileDAL(){}

    public ProfileDAL(UUID profileId, UUID userId, Privacy privacy, String profileStatus, List<UUID> followerIds){
        this.profileStatus = profileStatus;
        this.followerIds = followerIds;
        setProfileId(profileId);
        setUserId(userId);
        setProfilePrivacy(privacy);
    }

    public ProfileDAL(UUID profileId, UUID userId, String profileStatus, List<UUID> followerIds){
        this.profileStatus = profileStatus;
        this.followerIds = followerIds;
        setProfileId(profileId);
        setUserId(userId);
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
