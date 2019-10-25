package sourcecode.models.other.profile;

import sourcecode.models.other.post.Post;
import sourcecode.models.other.user.User;

import java.util.List;
import java.util.UUID;

public class Profile {

    private UUID profileId;
    private User profileUser;
    private List<User> followers;
    private List<User> following;
    private List<Post> profilePosts;
    private Privacy profilePrivacy;
    private String profileStatus;

    public Profile(){}

    public Profile(UUID profileId, User user, List<User> followers, List<User> following, List<Post> profilePosts, Privacy privacy, String profileStatus){
        this.profileStatus = profileStatus;
        setProfileId(profileId);
        setProfileUser(user);
        setFollowers(followers);
        setFollowing(following);
        setProfilePosts(profilePosts);
        setProfilePrivacy(privacy);
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public User getProfileUser() {
        return profileUser;
    }

    public void setProfileUser(User profileUser) {
        this.profileUser = profileUser;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Post> getProfilePosts() {
        return profilePosts;
    }

    public void setProfilePosts(List<Post> profilePosts) {
        this.profilePosts = profilePosts;
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
}
