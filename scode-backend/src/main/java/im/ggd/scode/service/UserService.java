package im.ggd.scode.service;

import im.ggd.scode.dto.request.CreateUserRequest;
import im.ggd.scode.entity.UserEntity;

public interface UserService {

    public boolean isUsernameExists(String username);

    public boolean isEmailExists(String email);

    public UserEntity findByUsername(String username);

    public UserEntity findByEmail(String email);

    public UserEntity store(CreateUserRequest request);

}
