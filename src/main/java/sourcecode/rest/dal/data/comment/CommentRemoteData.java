package sourcecode.rest.dal.data.comment;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sourcecode.models.dal.post.CommentDAL;
import sourcecode.rest.dal.interfaces.CommentContext;
import sourcecode.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommentRemoteData implements CommentContext {
    @Override
    public List<CommentDAL> getCommentsByPostId(UUID postId) {
        List<CommentDAL> commentDALS = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CommentDAL where postId = :postId");
            query.setParameter("postId", postId);

            commentDALS = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commentDALS;
    }

    @Override
    public void addComment(UUID postId, UUID userId, String comment) {
        CommentDAL commentDAL = new CommentDAL(UUID.randomUUID(), comment, userId, postId, new Date());
        HibernateUtil.save(commentDAL);
    }
}
