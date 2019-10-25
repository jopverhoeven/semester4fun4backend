package sourcecode.rest.dal.data.post;

import sourcecode.models.dal.post.PostDAL;
import sourcecode.models.other.user.User;
import sourcecode.rest.dal.interfaces.PostContext;

import java.util.*;

public class PostLocalData implements PostContext {

    private static ArrayList<PostDAL> posts = new ArrayList<>();

    public PostLocalData() {
        createPosts();
    }

    private void createPosts() {
        PostDAL post1 = new PostDAL(
                UUID.fromString("0f6bae5d-2554-41a1-baf2-a86d5a5a6937"),
                "Post1",
                "https://www.paradijsvogelsmagazine.nl/wp-content/uploads/2016/12/Schermafdruk-2016-12-15-14.13.25-1110x600.png",
                UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"),
                new ArrayList<UUID>(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 9, 12, 00, 00).getTime()
                );

        PostDAL post2 = new PostDAL(
                UUID.fromString("0bc6ff26-c4ef-48f5-adce-92ecfbfd5d43"),
                "Post2",
                "https://images1.persgroep.net/rcs/t0NSNpmMqs4zzv38U2I24Z1puzs/diocontent/117346211/_fitwidth/694/?appId=21791a8992982cd8da851550a453bd7f&quality=0.9",
                UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"),
                new ArrayList<UUID>(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 7, 12, 00, 00).getTime()
        );

        PostDAL post3 = new PostDAL(
                UUID.fromString("ecc1dabc-9c04-430f-afd9-7ae0b96f728c"),
                "Post3",
                "https://www.bakkeveen.nl/wp-content/uploads/2016/10/paddestoel.jpg",
                UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"),
                new ArrayList<UUID>(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 8, 12, 10, 00).getTime()
        );

        PostDAL post4 = new PostDAL(
                UUID.fromString("79858764-90f3-4f90-9b03-066b7935ffd8"),
                "Post4",
                "https://upload.wikimedia.org/wikipedia/commons/b/b4/Dalen_Hotel-2011.jpg",
                UUID.fromString("54bf8482-1a58-4a99-84d5-3c2278147ffa"),
                new ArrayList<UUID>(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 8, 12, 00, 00).getTime()
        );

        PostDAL post5 = new PostDAL(
                UUID.fromString("b6c09b59-4697-4368-b5fe-4ded39d1b20f"),
                "Post1",
                "https://www.paradijsvogelsmagazine.nl/wp-content/uploads/2016/12/Schermafdruk-2016-12-15-14.13.25-1110x600.png",
                UUID.fromString("a96a8b4a-725f-4078-a957-3de1292d35d5"),
                new ArrayList<UUID>(),
                new GregorianCalendar(2019, Calendar.SEPTEMBER, 9, 12, 00, 00).getTime()
        );


        posts.addAll(Arrays.asList(post1, post2, post3, post4, post5));
    }


    @Override
    public PostDAL getPostById(UUID postId) {

        PostDAL returnPost = null;

        for(PostDAL post : posts){
            if (post.getPostId().compareTo(postId) == 0){
                returnPost = post;
            }
        }

        return returnPost;
    }

    @Override
    public List<PostDAL> getPostFromUser(UUID userId, int from, int to) {

        List<PostDAL> returnPosts = new ArrayList<>();

        for(PostDAL post : posts){
            if (post.getUserId().compareTo(userId) == 0){
                returnPosts.add(post);
            }
        }

        return returnPosts;
    }

    @Override
    public List<PostDAL> getPosts() {
        return posts;
    }

    @Override
    public boolean hasLiked(UUID postId, UUID userId) {
        PostDAL post = getPostById(postId);

        return post.getLikes().contains(userId);
    }

    @Override
    public void addLike(UUID postId, UUID userId) {
        PostDAL postDAL = getPostById(postId);

        postDAL.getLikes().add(userId);
    }

    @Override
    public void removeLike(UUID postId, UUID userId) {
        PostDAL postDAL = getPostById(postId);
        postDAL.getLikes().remove(userId);
    }

    @Override
    public List<UUID> getLikes(UUID postId) {
        PostDAL postDAL = getPostById(postId);
        List<UUID> likes = new ArrayList<>();

        for (UUID uuid : postDAL.getLikes()) {
            likes.add(uuid);
        }

        return likes;
    }

    @Override
    public PostDAL addPost(User user, String image, String description) {
        PostDAL newPost = new PostDAL(
                UUID.randomUUID(),
                description,
                image,
                user.getUserId(),
                new ArrayList<UUID>(),
                new Date()
        );

        posts.add(newPost);

        return newPost;
    }
}
