package sourcecode.models.other.profile;

public class Privacy {

    private boolean publicProfile;

    public Privacy(){

    }

    public Privacy(boolean publicProfile){
        setPublicProfile(publicProfile);
    }

    public boolean isPublicProfile() {
        return publicProfile;
    }

    public void setPublicProfile(boolean publicProfile) {
        this.publicProfile = publicProfile;
    }
}
