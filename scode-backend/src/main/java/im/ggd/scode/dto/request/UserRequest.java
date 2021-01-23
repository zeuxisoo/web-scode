package im.ggd.scode.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import im.ggd.scode.validator.uniq.UniqEmail;
import im.ggd.scode.validator.uniq.UniqUsername;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty(message = "Please enter username")
    @Size(min = 4, message = "Username must more than 4 letters")
    @UniqUsername(message = "Username already exists")
    private String username;

    @NotEmpty(message = "Please enter password")
    @Size(min = 8, message = "Password must more than 8 letters")
    private String password;

    @NotEmpty(message = "Please enter email")
    @Email(message = "Email format incorrect")
    @UniqEmail(message = "Email already exists")
    private String email;

}
