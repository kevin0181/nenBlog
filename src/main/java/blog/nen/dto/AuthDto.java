package blog.nen.dto;

public class AuthDto {
    String email;
    boolean auth;

    public AuthDto(boolean auth) {
        this.auth = auth;
    }

    public AuthDto() {

    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
