package sourcecode.models.dal.post;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "post")
public class PostNoImageDAL {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "postId", length = 36)
    private UUID postId;

    @Column(name = "postDescription")
    private String postDescription;

    @Type(type = "uuid-char")
    @Column(name = "userId", length = 36)
    private UUID userId;

    @Transient
    private List<UUID> likes = new ArrayList<>();

    @Column(name = "postDate")
    private Date postDate;

    public PostNoImageDAL() {
    }

    public PostNoImageDAL(UUID postId, String postDescription, UUID userId, Date postDate) {
        setPostId(postId);
        setPostDescription(postDescription);
        setUserId(userId);
        setPostDate(postDate);
    }

    public PostNoImageDAL(UUID postId, String postDescription, UUID userId, List<UUID> likes, Date postDate) {
        setPostId(postId);
        setPostDescription(postDescription);
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public List<UUID> getLikes() {
        return likes;
    }

    public void addLikes(List<LikeDAL> likeDALS) {
        for(LikeDAL likeDAL : likeDALS) {
            this.likes.add(likeDAL.getUserId());
        }
    }


    @Override
    public String toString() {
        return getPostId() + " " + getPostDescription();
    }

}
