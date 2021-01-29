package im.ggd.scode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import im.ggd.scode.model.UserModel;
import im.ggd.scode.repository.UserRepository;
import im.ggd.scode.security.component.JwtAuthentication;
import im.ggd.scode.security.model.JwtTokenModel;
import im.ggd.scode.dto.request.CreateUserRequest;
import im.ggd.scode.dto.request.SignInUserRequest;
import im.ggd.scode.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtAuthentication jwtAuthentication;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean isUsernameExists(String username) {
        UserModel user = this.findByUsername(username);

        return user != null;
    }

    @Override
    public boolean isEmailExists(String email) {
        UserModel user = this.findByEmail(email);

        return user != null;
    }

    @Override
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel store(CreateUserRequest request) {
        String password = passwordEncoder.encode(request.getPassword());

        UserModel user = new UserModel();
        user.setUsername(request.getUsername());
        user.setPassword(password);
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    @Override
    public JwtTokenModel signIn(SignInUserRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Authenticate username and password
        // - Throw AuthenticationException when invalid username or password
        Authentication authentication;

        authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);

        // Get authenticated user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generate jwt token
        JwtTokenModel jwtTokenModel = jwtAuthentication.createToken(userDetails.getUsername());

        return jwtTokenModel;
    }

}
