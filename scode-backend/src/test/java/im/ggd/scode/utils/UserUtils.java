package im.ggd.scode.utils;

import im.ggd.scode.entity.UserEntity;

public class UserUtils {

    public static UserEntity createUser(String username, String password, String email) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

}
