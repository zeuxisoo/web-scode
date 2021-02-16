package im.ggd.scode.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.exception.UserNotFoundException;
import im.ggd.scode.service.UserService;

@Component
public class CurrentUser {

    @Autowired
    private UserService userService;

    public UserEntity getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            UserEntity userEntity = userService.findByUsername(userDetails.getUsername());

            return userEntity;
        }

        throw new UserNotFoundException();
    }

    public Long getId() {
        return getUser().getId();
    }

}
