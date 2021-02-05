package im.ggd.scode.utils;

import org.springframework.stereotype.Component;

import im.ggd.scode.entity.UserEntity;

@Component
public class UserUtils {

    public UserEntity createUser(String username, String password, String email) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

}
