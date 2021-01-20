package im.ggd.scode.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date createdAt;
    private Date updatedAt;

}
