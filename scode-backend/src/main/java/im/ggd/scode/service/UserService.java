package im.ggd.scode.service;

import im.ggd.scode.model.User;
import im.ggd.scode.request.UserRequest;

public interface UserService {

    public boolean isUsernameExists(String username);

    public User findByUsername(String username);

    public User store(UserRequest userRequest);

}
