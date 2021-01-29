package im.ggd.scode.service;

import im.ggd.scode.model.UserModel;
import im.ggd.scode.security.model.JwtTokenModel;
import im.ggd.scode.dto.request.CreateUserRequest;
import im.ggd.scode.dto.request.SignInUserRequest;

public interface UserService {

    public boolean isUsernameExists(String username);

    public boolean isEmailExists(String email);

    public UserModel findByUsername(String username);

    public UserModel findByEmail(String email);

    public UserModel store(CreateUserRequest createUserRequest);

    public JwtTokenModel signIn(SignInUserRequest signInUserRequest);

}
