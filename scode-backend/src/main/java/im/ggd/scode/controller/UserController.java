package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.dto.request.CreateUserRequest;
import im.ggd.scode.dto.request.SignInUserRequest;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.dto.transformer.JwtTokenTransformer;
import im.ggd.scode.dto.transformer.UserTransformer;
import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.security.model.JwtTokenModel;
import im.ggd.scode.service.UserService;
import im.ggd.scode.validation.group.order.BasicOrder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ItemResponse create(@RequestBody @Validated({ BasicOrder.class }) CreateUserRequest request) {
        UserEntity user = userService.store(request);

        return new ItemResponse(user, new UserTransformer());
    }

    @PostMapping("/signin")
    public ItemResponse signIn(@RequestBody @Validated({ BasicOrder.class }) SignInUserRequest request) {
        JwtTokenModel token = userService.signIn(request);

        return new ItemResponse(token, new JwtTokenTransformer());
    }

    @GetMapping("/me")
    public String me() {
        return "My page need signed in";
    }

}
