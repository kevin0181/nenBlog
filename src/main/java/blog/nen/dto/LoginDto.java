package blog.nen.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String ErrorCode;

    public LoginDto(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }
}
