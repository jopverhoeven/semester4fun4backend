package sourcecode.models.other.post;

import sourcecode.models.other.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Post implements Comparable<Post>{

    private UUID postId;
    private String postDescription;
    private String imageContent;
    private User postUser;
    private List<Comment> comments;
    private List<User> likes;
    private Date postDate;

    public Post(){}

    public Post(UUID postId, String postDescription, String imageContent, User postUser, List<Comment> comments, List<User> likes, Date postDate){
        setPostId(postId);
        setPostDescription(postDescription);
        setImageContent(imageContent);
        setPostUser(postUser);
        setComments(comments);
        setLikes(likes);
        setPostDate(postDate);
    }

    public Post(UUID postId, String postDescription, User postUser, List<Comment> postComments, List<User> userLikes, Date postDate) {
        setPostId(postId);
        setPostDescription(postDescription);
        setPostUser(postUser);
        setComments(postComments);
        setLikes(userLikes);
        setPostDate(postDate);
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public int compareTo(Post post){
        if (post.getPostDate() == null || getPostDate() == null){
            return 0;
        }

        return getPostDate().compareTo(post.getPostDate());
    }

}
