package im.ggd.scode.dto.request;

import javax.validation.constraints.NotEmpty;

import im.ggd.scode.validation.group.EmptyGroup;
import lombok.Data;

@Data
public class AuthSignInRequest {

    @NotEmpty(message = "Please enter username", groups = EmptyGroup.class)
    private String username;

    @NotEmpty(message = "Please enter password", groups = EmptyGroup.class)
    private String password;

}
