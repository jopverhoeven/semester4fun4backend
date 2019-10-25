package sourcecode.rest.dal.data.post;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sourcecode.models.dal.post.LikeDAL;
import sourcecode.models.dal.post.PostDAL;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.PostContext;
import sourcecode.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostRemoteData implements PostContext {

    @Override
    public PostDAL getPostById(UUID postId) {
        Transaction transaction = null;
        PostDAL postDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from PostDAL where postId = :postId");
            query.setParameter("postId", postId);

            postDAL = (PostDAL) query.getSingleResult();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        addLikesToPost(transaction, postDAL);

        return postDAL;
    }

    @Override
    public List<PostDAL> getPostFromUser(UUID userId, int from, int to) {
        Transaction transaction = null;
        List<PostDAL> postDAL = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from PostDAL where userId = :userId");
            query.setParameter("userId", userId);

            postDAL = query.list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        for (PostDAL post : postDAL) {
            addLikesToPost(transaction, post);
        }

        return postDAL;
    }

    @Override
    public List<PostDAL> getPosts() {
        Transaction transaction = null;

        List<PostDAL> postDALS = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            postDALS = session.createQuery("from PostDAL", PostDAL.class).list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        for (PostDAL post : postDALS) {
            addLikesToPost(transaction, post);
        }

        return postDALS;
    }

    @Override
    public boolean hasLiked(UUID postId, UUID userId) {
        return false;
    }

    @Override
    public void addLike(UUID postId, UUID userId) {

    }

    @Override
    public void removeLike(UUID postId, UUID userId) {

    }

    @Override
    public List<UUID> getLikes(UUID postId) {
        return null;
    }

    @Override
    public void addPost(User user, String image, String description) {

    }

    private void addLikesToPost(Transaction transaction, PostDAL postDAL) {
        List<LikeDAL> likeDALS = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from LikeDAL where postId = :id");
            query.setParameter("id", postDAL.getPostId());

            likeDALS = query.list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        postDAL.addLikes(likeDALS);
    }
}
