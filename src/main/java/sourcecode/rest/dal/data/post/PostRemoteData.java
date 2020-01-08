package sourcecode.rest.dal.data.post;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sourcecode.models.dal.post.LikeDAL;
import sourcecode.models.dal.post.PostDAL;
import sourcecode.models.dal.post.PostNoImageDAL;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.PostContext;
import sourcecode.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostRemoteData implements PostContext {

    @Override
    public PostDAL getPostById(UUID postId) {
        PostDAL postDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from PostDAL where postId = :postId");
            query.setParameter("postId", postId);

            postDAL = (PostDAL) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addLikesToPost(postDAL);

        return postDAL;
    }


    @Override
    public PostNoImageDAL getPostByIdNoImage(UUID postId) {
        PostNoImageDAL postDAL = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from PostNoImageDAL where postId = :postId");
            query.setParameter("postId", postId);

            postDAL = (PostNoImageDAL) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addLikesToPost(postDAL);

        return postDAL;
    }

    @Override
    public List<PostDAL> getPostFromUser(UUID userId, int from, int to) {
        List<PostDAL> postDAL = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from PostDAL where userId = :userId");
            query.setParameter("userId", userId);

            postDAL = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (PostDAL post : postDAL) {
            addLikesToPost(post);
        }

        return postDAL;
    }

    @Override
    public List<PostDAL> getPosts() {
        List<PostDAL> postDALS = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            postDALS = session.createQuery("from PostDAL", PostDAL.class).list();

            for (PostDAL post : postDALS) {
                addLikesToPost(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postDALS;
    }

    @Override
    public List<PostNoImageDAL> getPostsNoImage() {
        List<PostNoImageDAL> postDALS = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            postDALS = session.createQuery("from PostNoImageDAL", PostNoImageDAL.class).list();

            for (PostNoImageDAL post : postDALS) {
                addLikesToPost(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return postDALS;
    }

    @Override
    public boolean hasLiked(UUID postId, UUID userId) {
        PostDAL postDAL = getPostById(postId);

        return postDAL.getLikes().contains(userId);
    }

    @Override
    public void addLike(UUID postId, UUID userId) {
        LikeDAL likeDAL = new LikeDAL(postId, userId);

        HibernateUtil.save(likeDAL);
    }

    @Override
    public void removeLike(UUID postId, UUID userId) {
        LikeDAL likeDAL = new LikeDAL(postId, userId);

        HibernateUtil.delete(likeDAL);
    }

    @Override
    public List<UUID> getLikes(UUID postId) {
        PostDAL postDAL = getPostById(postId);

        return postDAL.getLikes();
    }

    @Override
    public PostDAL addPost(User user, String image, String description) {
        PostDAL post = new PostDAL(UUID.randomUUID(), description, image, user.getUserId(), new Date());
        HibernateUtil.save(post);

        return post;
    }


    private void addLikesToPost(PostDAL postDAL) {
        List<LikeDAL> likeDALS = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from LikeDAL where postId = :postId");
            query.setParameter("postId", postDAL.getPostId());

            likeDALS = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        postDAL.addLikes(likeDALS);
    }

    private void addLikesToPost(PostNoImageDAL postDAL) {
        List<LikeDAL> likeDALS = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from LikeDAL where postId = :postId");
            query.setParameter("postId", postDAL.getPostId());

            likeDALS = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        postDAL.addLikes(likeDALS);
    }
}
