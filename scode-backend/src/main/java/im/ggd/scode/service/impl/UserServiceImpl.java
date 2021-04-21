package im.ggd.scode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import im.ggd.scode.repository.UserRepository;
import im.ggd.scode.dto.request.UserCreateRequest;
import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.service.UserService;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean isUsernameExists(String username) {
        UserEntity user = this.findByUsername(username);

        return user != null;
    }

    @Override
    public boolean isEmailExists(String email) {
        UserEntity user = this.findByEmail(email);

        return user != null;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity store(UserCreateRequest request) {
        String password = passwordEncoder.encode(request.getPassword());

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(password);
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

}
