package im.ggd.scode.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty(message = "Please enter username")
    @Size(min = 4, message = "Username must more than 4 letters")
    private String username;

    @NotEmpty(message = "Please enter password")
    @Size(min = 8, message = "Password must more than 8 letters")
    private String password;

    @NotEmpty(message = "Please enter email")
    @Email(message = "Email format incorrect")
    private String email;

}
