package sourcecode.rest.logic;

import sourcecode.models.dal.profile.ProfileDAL;
import sourcecode.models.other.post.Post;
import sourcecode.models.other.profile.Profile;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

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

        //TODO: Convert UUIDS from ProfileDAL Following and Followers to actual models
        Profile returnProfile = new Profile(
                profileDAL.getProfileId(),
                profileUser,
                new ArrayList<User>(),
                new ArrayList<User>(),
                profilePosts,
                profileDAL.getProfilePrivacy()
        );

        return returnProfile;
    }

}
