package sourcecode.models.controller.post;

import java.util.UUID;

public class AddPostSubmitModel {
    private UUID token;
    private String image;
    private String description;

    public AddPostSubmitModel() {}

    public AddPostSubmitModel(UUID token, String image, String description) {
        this.token = token;
        this.image = image;
        this.description = description;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
