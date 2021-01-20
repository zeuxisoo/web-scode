package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.model.vo.UserVO;
import im.ggd.scode.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String create(@RequestBody UserVO userVO) {
        if (userService.isUsernameExists(userVO.getUsername())) {
            return "Username already exists";
        }

        userService.store(userVO);

        return "user created";
    }

}
