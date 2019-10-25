package sourcecode.models.controller.profile;

import sourcecode.models.other.error.ApiError;

public class FollowProfileReturnModel {
    private ApiError apiError;
    private boolean following;

    public FollowProfileReturnModel() {}

    public FollowProfileReturnModel(ApiError apiError, boolean following) {
        this.apiError = apiError;
        this.following = following;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
