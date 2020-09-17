package blog.nen.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class SignUpDto {
    @NotBlank
    @Email
    @Size(min = 1)
    private String Email;
    //    @NotEmpty
    private String Password;
    //    @NotEmpty
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
