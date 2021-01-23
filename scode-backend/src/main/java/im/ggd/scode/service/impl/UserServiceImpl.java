package im.ggd.scode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import im.ggd.scode.model.User;
import im.ggd.scode.repository.UserRepository;
import im.ggd.scode.request.UserRequest;
import im.ggd.scode.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isUsernameExists(String username) {
        User user = this.findByUsername(username);

        return user != null;
    }

    @Override
    public boolean isEmailExists(String email) {
        User user = this.findByEmail(email);

        return user != null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User store(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

}
