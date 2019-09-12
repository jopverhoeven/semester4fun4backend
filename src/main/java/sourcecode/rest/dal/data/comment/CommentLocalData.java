package sourcecode.rest.dal.data.comment;

import sourcecode.models.dal.post.CommentDAL;
import sourcecode.rest.dal.interfaces.CommentContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommentLocalData implements CommentContext {


    private static ArrayList<CommentDAL> comments = new ArrayList<>();

    public CommentLocalData() {
        createComments();
    }

    private void createComments() {

        CommentDAL comment1 = new CommentDAL(
                UUID.fromString("d4f62c73-043d-4df4-9351-6318557503c7"),
                "Dit is een comment",
                UUID.fromString("e7019164-6b27-47ba-b162-cf0d2a07d186"),
                UUID.fromString("0f6bae5d-2554-41a1-baf2-a86d5a5a6937"),
                new Date()
        );

        comments.add(comment1);
    }


    @Override
    public List<CommentDAL> getCommentsByPostId(UUID postId) {
        ArrayList<CommentDAL> returnComments = new ArrayList<>();

        for(CommentDAL comment : comments){
            if (comment.getPostId().compareTo(postId) == 0){
                returnComments.add(comment);
            }
        }

        return returnComments;
    }

    @Override
    public void addComment(UUID postId, UUID userId, String comment) {
        UUID commentId = UUID.randomUUID();

        CommentDAL commentDAL = new CommentDAL(
                commentId,
                comment,
                userId,
                postId,
                new Date()
        );

        comments.add(commentDAL);
    }
}
