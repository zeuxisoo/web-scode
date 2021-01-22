package im.ggd.scode.request;

import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date createdAt;
    private Date updatedAt;

}
