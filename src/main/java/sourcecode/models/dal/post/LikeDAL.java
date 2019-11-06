package sourcecode.models.dal.post;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "post_likes")
public class LikeDAL {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "postId", length = 36)
    private UUID postId;

    @Type(type = "uuid-char")
    @Column(name = "userId", length = 36)
    private UUID userId;

    public LikeDAL() {}

    public LikeDAL(UUID postId, UUID userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }
}
