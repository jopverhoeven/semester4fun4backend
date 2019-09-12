package sourcecode.models.dal.post;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostDAL {

    private UUID postId;
    private String postDescription;
    private String imageContent;
    private UUID userId;
    private List<UUID> likes;
    private Date postDate;

    public PostDAL(){}

    public PostDAL(UUID postId, String postDescription, String imageContent, UUID userId,  List<UUID> likes, Date postDate){
        setPostId(postId);
        setPostDescription(postDescription);
        setImageContent(imageContent);
        setUserId(userId);
        setLikes(likes);
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<UUID> getLikes() {
        return likes;
    }

    public void setLikes(List<UUID> likes) {
        this.likes = likes;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
