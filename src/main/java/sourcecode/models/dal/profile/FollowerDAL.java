package sourcecode.models.dal.profile;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "PROFILE_FOLLOWING")
public class FollowerDAL {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "PROFILE_ID", length = 36)
    private UUID profileId;

    @Type(type = "uuid-char")
    @Column(name = "FOLLOWING_ID", length = 36)
    private UUID followingId;

    public FollowerDAL(){}

    public FollowerDAL(UUID profileId, UUID followingId) {
        this.profileId = profileId;
        this.followingId = followingId;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public UUID getFollowingId() {
        return followingId;
    }

    public void setFollowingId(UUID followingId) {
        this.followingId = followingId;
    }
}
