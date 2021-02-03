package im.ggd.scode.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import im.ggd.scode.validation.constraints.unique.UniqueEmail;
import im.ggd.scode.validation.constraints.unique.UniqueUsername;
import im.ggd.scode.validation.group.EmptyGroup;
import im.ggd.scode.validation.group.ExistsGroup;
import im.ggd.scode.validation.group.LengthGroup;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class UserCreateRequest {

    @NotEmpty(message = "Please enter username", groups = EmptyGroup.class)
    @Size(min = 4, message = "Username must more than 4 letters", groups = LengthGroup.class)
    @UniqueUsername(message = "Username already exists", groups = ExistsGroup.class)
    private String username;

    @NotEmpty(message = "Please enter password", groups = EmptyGroup.class)
    @Size(min = 8, message = "Password must more than 8 letters", groups = LengthGroup.class)
    private String password;

    @NotEmpty(message = "Please enter email", groups = EmptyGroup.class)
    @Email(message = "Email format incorrect", groups = LengthGroup.class)
    @UniqueEmail(message = "Email already exists", groups = ExistsGroup.class)
    private String email;

}
