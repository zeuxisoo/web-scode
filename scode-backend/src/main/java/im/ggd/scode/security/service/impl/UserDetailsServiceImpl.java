package im.ggd.scode.security.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import im.ggd.scode.model.UserModel;
import im.ggd.scode.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Cannot not found user by " + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                // .authorities(new SimpleGrantedAuthority("ADMIN"))
                .authorities(new ArrayList<SimpleGrantedAuthority>() {{
                    new SimpleGrantedAuthority("ADMIN");
                    new SimpleGrantedAuthority("USER");
                }})
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
