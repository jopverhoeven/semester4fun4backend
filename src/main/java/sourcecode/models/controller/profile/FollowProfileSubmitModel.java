package sourcecode.models.controller.profile;

import java.util.UUID;

public class FollowProfileSubmitModel {
    private UUID token;
    private UUID profileId;

    public FollowProfileSubmitModel() {}

    public FollowProfileSubmitModel(UUID token, UUID profileId) {
        this.token = token;
        this.profileId = profileId;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }
}
