package Post;

import org.junit.Assert;
import org.junit.Test;
import sourcecode.models.other.post.Post;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.data.comment.CommentLocalData;
import sourcecode.rest.dal.data.post.PostLocalData;
import sourcecode.rest.dal.data.user.UserLocalData;
import sourcecode.rest.dal.repository.CommentRepository;
import sourcecode.rest.dal.repository.PostRepository;
import sourcecode.rest.dal.repository.UserRepository;
import sourcecode.rest.logic.CommentManager;
import sourcecode.rest.logic.PostManager;
import sourcecode.rest.logic.UserManager;

public class PostTest {

    private PostManager postManager;
    private PostRepository postRepository;

    private UserManager userManager;
    private UserRepository userRepository;

    private CommentManager commentManager;
    private CommentRepository commentRepository;

    @Test
    public void createNewPost() {
        commentRepository = new CommentRepository(new CommentLocalData());
        commentManager = new CommentManager(commentRepository);

        postRepository = new PostRepository(new PostLocalData());
        postManager = new PostManager(postRepository);

        userRepository = new UserRepository(new UserLocalData());
        userManager = new UserManager(userRepository);

        String username = "GetUser02";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        String imageContent = "path/to/image.png";
        String description = "post descripiton";

        postManager.addPost(user, imageContent, description);

        Assert.assertTrue(postManager.getPostByUser(user.getUserId()).get(0) != null);
    }

    @Test
    public void getPostByUser() {
        commentRepository = new CommentRepository(new CommentLocalData());
        commentManager = new CommentManager(commentRepository);

        postRepository = new PostRepository(new PostLocalData());
        postManager = new PostManager(postRepository);

        userRepository = new UserRepository(new UserLocalData());
        userManager = new UserManager(userRepository);

        String username = "GetUser02";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        String imageContent = "path/to/image.png";
        String description = "post descripiton";

        Post post = postManager.addPost(user, imageContent, description).getPost();

        Assert.assertTrue(postManager.getPostByUser(user.getUserId()).get(0) != null);
    }

    @Test
    public void getPostById() {
        commentRepository = new CommentRepository(new CommentLocalData());
        commentManager = new CommentManager(commentRepository);

        postRepository = new PostRepository(new PostLocalData());
        postManager = new PostManager(postRepository);

        userRepository = new UserRepository(new UserLocalData());
        userManager = new UserManager(userRepository);

        String username = "GetUser02";
        String firstname = "Test";
        String lastname = "User";
        String profilePicture = "path/to/image.png";

        User user = userManager.addUser(username, firstname, lastname, profilePicture);

        String imageContent = "path/to/image.png";
        String description = "post descripiton";

        Post post = postManager.addPost(user, imageContent, description).getPost();

        Post postById = postManager.getPostById(post.getPostId());

        Assert.assertTrue(post.getPostId() == postById.getPostId());
    }

}
