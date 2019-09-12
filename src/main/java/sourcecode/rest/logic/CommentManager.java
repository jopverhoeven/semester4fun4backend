package sourcecode.rest.logic;

import sourcecode.models.dal.post.CommentDAL;
import sourcecode.models.manager.comment.AddCommentModel;
import sourcecode.models.other.error.ApiError;
import sourcecode.models.other.error.ApiErrorMessage;
import sourcecode.models.other.post.Comment;
import sourcecode.models.other.post.Post;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.repository.CommentRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CommentManager {

    private CommentRepository commentRepository = new CommentRepository();
    private static UserManager userManager = new UserManager();
    private static PostManager postManager = new PostManager();

    public List<Comment> getCommentsByPostId(UUID postId){
        List<CommentDAL> comments = commentRepository.getCommentByPostId(postId);
        List<Comment> returnComments = new ArrayList<>();

        for(CommentDAL comment : comments){
            returnComments.add(convertCommentDALtoComment(comment));
        }

        Collections.sort(returnComments);
        Collections.reverse(returnComments);

        return returnComments;
    }


    public AddCommentModel addComment(User user, UUID postId, String commentContent) {
        Post post = postManager.getPostById(postId);

        if (post == null){
            return new AddCommentModel(null, ApiError.getError(ApiErrorMessage.ID_INCORRECT));
        }

        commentRepository.addComment(post.getPostId(), user.getUserId(), commentContent);

        return new AddCommentModel(getCommentsByPostId(post.getPostId()), null);
    }

    private Comment convertCommentDALtoComment(CommentDAL commentDAL){
        User user = userManager.getUser(commentDAL.getUserId());

        Comment returnComment = new Comment(
                commentDAL.getCommentId(),
                commentDAL.getCommentContent(),
                user,
                commentDAL.getCommentDate()
        );

        return returnComment;
    }

}
