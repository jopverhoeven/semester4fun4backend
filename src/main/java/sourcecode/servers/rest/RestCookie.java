package sourcecode.servers.rest;

public enum RestCookie {

    TOKEN("Token");


    public final String cookieKey;

    RestCookie(String cookieKey){
        this.cookieKey = cookieKey;
    }

}
