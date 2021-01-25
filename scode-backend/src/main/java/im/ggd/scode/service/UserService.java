package im.ggd.scode.service;

import im.ggd.scode.model.User;
import im.ggd.scode.dto.request.CreateUserRequest;

public interface UserService {

    public boolean isUsernameExists(String username);

    public boolean isEmailExists(String email);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User store(CreateUserRequest userRequest);

}
