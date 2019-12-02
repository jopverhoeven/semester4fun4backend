package sourcecode.rest.dal.data.authentication;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sourcecode.models.dal.authentication.AuthenticationDAL;
import sourcecode.rest.dal.interfaces.AuthenticationContext;
import sourcecode.util.HibernateUtil;

import java.util.UUID;

public class AuthenticationRemoteData implements AuthenticationContext {
    @Override
    public boolean login(UUID userId, String password) {
        boolean loggedIn = false;
        AuthenticationDAL authDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query =  session.createQuery("from AuthenticationDAL where userId = :userId");
            query.setParameter("userId", userId);

            authDAL = (AuthenticationDAL) query.getSingleResult();
        } catch(Exception e){
            loggedIn = false;
        }

        if (authDAL.getPassword().equals(password)){
            loggedIn = true;
        }

        return loggedIn;
    }

    @Override
    public void register(UUID userId, String password) {
        AuthenticationDAL authenticationDAL = new AuthenticationDAL(userId, password);
        HibernateUtil.save(authenticationDAL);
    }
}
