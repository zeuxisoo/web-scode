package im.ggd.scode.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserRequest {

    @NotEmpty
    @Size(min = 4)
    private String username;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    @Email
    private String email;

}
