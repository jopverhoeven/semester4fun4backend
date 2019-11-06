package sourcecode.rest.dal.data.user;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sourcecode.models.dal.user.UserDAL;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.UserContext;
import sourcecode.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRemoteData implements UserContext {
    @Override
    public User getUserById(UUID userId) {
        UserDAL userDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from UserDAL where userId = :userId");
            query.setParameter("userId", userId);

            userDAL = (UserDAL) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new User(userDAL);
    }

    @Override
    public List<User> getUserByName(String name) {
        List<User> users = new ArrayList<>();
        List<UserDAL> userDALS = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from UserDAL where username like concat('%',:name,'%') or firstname like concat('%',:name,'%') or lastname like concat('%',:name,'%')");
            query.setParameter("name", name);

            userDALS = query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        for (UserDAL userDAL : userDALS) {
            users.add(new User(userDAL));
        }

        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        UserDAL userDAL = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from UserDAL where username = :username");
            query.setParameter("username", username);

            userDAL = (UserDAL) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new User(userDAL);    }

    @Override
    public User addUser(String username, String firstname, String lastname, String profileImage) {
        UserDAL userDAL = new UserDAL(UUID.randomUUID(), username, firstname, lastname, profileImage);

        HibernateUtil.save(userDAL);

        return new User(userDAL);
    }

    @Override
    public void updateUserGeneral(UUID userId, String firstname, String lastname) {
        User user = getUserById(userId);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        UserDAL userDAL = new UserDAL(user.getUserId(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getProfilePicture());
        HibernateUtil.update(userDAL);
    }
}
