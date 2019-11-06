package sourcecode.rest.dal.data.profile;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sourcecode.models.dal.profile.FollowerDAL;
import sourcecode.models.dal.profile.ProfileDAL;
import sourcecode.models.other.profile.Privacy;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.ProfileContext;
import sourcecode.rest.logic.UserManager;
import sourcecode.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfileRemoteData implements ProfileContext {

    private UserManager userManager = new UserManager();

    @Override
    public ProfileDAL getProfileByUsername(String name) {
        User user = userManager.getUserByUsername(name);

        if (user == null)
            return null;

        ProfileDAL profileDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from ProfileDAL where userId = :userId");
            query.setParameter("userId", user.getUserId());

            profileDAL = (ProfileDAL) query.getSingleResult();


        } catch (Exception e) {
            e.printStackTrace();
        }

        profileDAL.setFollowerIds(addFollowersToProfile(profileDAL.getProfileId()));

        return profileDAL;
    }

    @Override
    public ProfileDAL getProfileById(UUID profileId) {
        ProfileDAL profileDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from ProfileDAL where profileId = :profileId");
            query.setParameter("profileId", profileId);

            profileDAL = (ProfileDAL) query.getSingleResult();


        } catch (Exception e) {
            e.printStackTrace();
        }

        profileDAL.setFollowerIds(addFollowersToProfile(profileDAL.getProfileId()));

        return profileDAL;
    }

    @Override
    public void addProfile(UUID userId) {
        ProfileDAL profileDAL = new ProfileDAL(
                UUID.randomUUID(),
                userId,
                new Privacy(true),
                "Hallo, ik ben lid van FonstaGram",
                new ArrayList<UUID>()
        );

        HibernateUtil.save(profileDAL);
    }

    @Override
    public List<UUID> getFollowingProfiles(UUID profileId) {
        ProfileDAL profileDAL = getProfileById(profileId);

        return profileDAL.getFollowerIds();
    }

    @Override
    public boolean followProfile(UUID userId, ProfileDAL profileDAL) {
        FollowerDAL followerDAL = new FollowerDAL(profileDAL.getProfileId(), userId);

        boolean isFollowing = getFollowingProfiles(profileDAL.getProfileId()).contains(userId);

        if (isFollowing){
            HibernateUtil.delete(followerDAL);
        }else {
            HibernateUtil.save(followerDAL);
        }

        return !isFollowing;
    }

    private List<UUID> addFollowersToProfile(UUID profileId) {
        List<FollowerDAL> followerDAL = new ArrayList<>();
        List<UUID> uuids = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from FollowerDAL where profileId = :profileId");
            query.setParameter("profileId", profileId);

            followerDAL = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (FollowerDAL dal : followerDAL) {
            uuids.add(dal.getFollowingId());
        }

        return uuids;
    }
}
