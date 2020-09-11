package blog.nen.dto;

public class SignUpDto {
    private String Email;
    private String Password;
    private String Phone;

    public SignUpDto(String email, String password, String phone) {
        Email = email;
        Password = password;
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
