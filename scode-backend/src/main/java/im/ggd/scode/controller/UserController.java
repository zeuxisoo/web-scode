package im.ggd.scode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.request.UserRequest;
import im.ggd.scode.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String create(@Valid @RequestBody UserRequest userRequest) {
        if (userService.isUsernameExists(userRequest.getUsername())) {
            return "Username already exists";
        }

        userService.store(userRequest);

        return "user created";
    }

}
