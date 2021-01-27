package im.ggd.scode.service;

import im.ggd.scode.model.UserModel;
import im.ggd.scode.dto.request.CreateUserRequest;

public interface UserService {

    public boolean isUsernameExists(String username);

    public boolean isEmailExists(String email);

    public UserModel findByUsername(String username);

    public UserModel findByEmail(String email);

    public UserModel store(CreateUserRequest userRequest);

}
