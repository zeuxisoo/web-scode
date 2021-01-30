package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.dto.request.CreateUserRequest;
import im.ggd.scode.dto.request.SignInUserRequest;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.dto.transformer.UserTransformer;
import im.ggd.scode.model.UserModel;
import im.ggd.scode.service.UserService;
import im.ggd.scode.validation.group.order.BasicOrder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ItemResponse create(@RequestBody @Validated({ BasicOrder.class }) CreateUserRequest request) {
        UserModel user = userService.store(request);

        return new ItemResponse(user, new UserTransformer());
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody @Validated({ BasicOrder.class }) SignInUserRequest request) {
        System.out.println(userService.signIn(request));

        return "user signed in";
    }

}
