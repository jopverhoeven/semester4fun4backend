package sourcecode.rest.dal.data.profile;

import sourcecode.models.dal.profile.ProfileDAL;
import sourcecode.models.other.profile.Privacy;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.ProfileContext;
import sourcecode.rest.logic.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ProfileLocalData implements ProfileContext {

    private static ArrayList<ProfileDAL> profiles = new ArrayList<>();

    private UserManager userManager = new UserManager();

    public ProfileLocalData() {
        createProfiles();
    }

    private void createProfiles() {
        ProfileDAL profile1 = new ProfileDAL(
                UUID.fromString("438ff83a-d197-4db6-8683-d24ae27603de"),
                UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"),
                new Privacy(true),
                "Hait",
                new ArrayList<UUID>()
        );

        ProfileDAL profile2 = new ProfileDAL(
                UUID.fromString("814c777a-1285-4e08-8314-3daa41b6dbe4"),
                UUID.fromString("e7019164-6b27-47ba-b162-cf0d2a07d186"),
                new Privacy(true),
                "😂😂😂😂",
                new ArrayList<>(Arrays.asList(profile1.getUserId()))
                );

        ProfileDAL profile3 = new ProfileDAL(
                UUID.fromString("63580342-6cd8-41fc-af77-41171dad31ed"),
                UUID.fromString("54bf8482-1a58-4a99-84d5-3c2278147ffa"),
                new Privacy(true),
                "🤷‍♂️🤷‍♂️🤷‍♂️",
                new ArrayList<>(Arrays.asList(profile1.getUserId(), profile2.getUserId()))
        );


        ProfileDAL profile4 = new ProfileDAL(
                UUID.fromString("b7dc992a-afdd-4ac1-80e1-bb16cadf30e7"),
                UUID.fromString("64198b11-17cc-438c-b1f7-23637330d10a"),
                new Privacy(true),
                "🎶🎶💖💖",
                new ArrayList<>(Arrays.asList(profile1.getUserId(), profile2.getUserId(), profile3.getUserId()))
        );

        profiles.addAll(Arrays.asList(profile1, profile2, profile3, profile4));
    }


    @Override
    public ProfileDAL getProfileByUsername(String username) {
        ProfileDAL returnProfile = null;

        User user = userManager.getUserByUsername(username);

        if (user == null)
            return null;

        for (ProfileDAL profile : profiles) {

            if (profile.getUserId().compareTo(user.getUserId()) == 0) {
                returnProfile = profile;
                break;
            }
        }

        return returnProfile;
    }

    @Override
    public ProfileDAL getProfileById(UUID profileId) {
        ProfileDAL returnProfile = null;

        for (ProfileDAL profile: profiles) {
            if (profile.getProfileId().compareTo(profileId) == 0) {
                returnProfile = profile;
                break;
            }
        }

        return returnProfile;
    }


    @Override
    public void addProfile(UUID userId) {
        ProfileDAL profile = new ProfileDAL(
                UUID.randomUUID(),
                userId,
                new Privacy(true),
                "Hallo! Ik ben lid van FonstaGram.",
                new ArrayList<UUID>()
        );

        profiles.add(profile);
    }

    @Override
    public List<UUID> getFollowingProfiles(UUID profileId) {
        List<UUID> userFollowingIds = new ArrayList<>();

        for(ProfileDAL profileDAL : profiles) {
            if (profileDAL.getProfileId().compareTo(profileId) == 0)
                continue;

            for (UUID followingId : profileDAL.getFollowerIds()) {
                if (followingId.compareTo(profileId) == 0) {
                    userFollowingIds.add(profileDAL.getUserId());
                }
            }
        }

        return userFollowingIds;
    }

    @Override
    public boolean followProfile(UUID userId, ProfileDAL profile) {
        boolean isFollowing = false;

        for (UUID followingUserId : profile.getFollowerIds()) {
            if (followingUserId.compareTo(userId) == 0) {
                isFollowing = true;
            }
        }

        if (isFollowing)
            profile.getFollowerIds().remove(userId);
        else
            profile.getFollowerIds().add(userId);

        return !isFollowing;
    }
}
