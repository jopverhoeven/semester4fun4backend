package sourcecode.rest.logic;

import sourcecode.models.dal.profile.ProfileDAL;
import sourcecode.models.manager.profile.FollowProfileModel;
import sourcecode.models.other.post.Post;
import sourcecode.models.other.profile.Profile;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.ProfileRepository;

import java.util.List;
import java.util.UUID;

public class ProfileManager {

    private ProfileRepository profileRepository = new ProfileRepository();
    private UserManager userManager = new UserManager();
    private PostManager postManager = new PostManager();

    public Profile getProfileByName(String username){

        ProfileDAL profileDAL = profileRepository.getProfileByUsername(username);

        if (profileDAL == null)
            return null;

        User profileUser = userManager.getUser(profileDAL.getUserId());
        List<Post> profilePosts = postManager.getPostByUser(profileDAL.getUserId());
        List<User> profileFollowers = userManager.convertUUIDsToUser(profileDAL.getFollowerIds());
        List<User> profileFollowing = this.getFollowingProfiles(profileDAL.getUserId());

        //TODO: Convert UUIDS from ProfileDAL Following and Followers to actual models
        Profile returnProfile = new Profile(
                profileDAL.getProfileId(),
                profileUser,
                profileFollowers,
                profileFollowing,
                profilePosts,
                profileDAL.getProfilePrivacy(),
                profileDAL.getProfileStatus());

        return returnProfile;
    }

    public List<User> getFollowingProfiles(UUID userId) {
        List<UUID> profileIds = this.profileRepository.getFollowingProfiles(userId);

        return userManager.convertUUIDsToUser(profileIds);
    }

    public void addProfile(UUID userId) {
        profileRepository.addProfile(userId);
    }

    public FollowProfileModel followProfile(User user, UUID profileId) {
        ProfileDAL profile = profileRepository.getProfileById(profileId);

        boolean isFollowing = profileRepository.followProfile(user.getUserId(), profile);

        FollowProfileModel followProfileModel = new FollowProfileModel(null, isFollowing);
        return followProfileModel;
    }
}
