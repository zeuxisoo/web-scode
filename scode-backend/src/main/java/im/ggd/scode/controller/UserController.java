package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.dto.request.UserRequest;
import im.ggd.scode.service.UserService;
import im.ggd.scode.validation.group.order.BasicOrder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String create(@RequestBody @Validated({ BasicOrder.class }) UserRequest userRequest) {
        userService.store(userRequest);

        return "user created";
    }

}
