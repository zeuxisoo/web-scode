package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.annotation.ApiController;
import im.ggd.scode.dto.converter.UserConverter;
import im.ggd.scode.dto.request.UserCreateRequest;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.service.UserService;
import im.ggd.scode.validation.group.order.BasicOrder;

@ApiController
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemResponse<UserEntity> itemResponse;

    @PostMapping("/create")
    public ItemResponse<?> create(@RequestBody @Validated({ BasicOrder.class }) UserCreateRequest request) {
        UserEntity user = userService.store(request);

        return itemResponse.ok(user, new UserConverter());
    }

    @GetMapping("/profile")
    public ItemResponse<?> profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findByUsername(username);

        return itemResponse.ok(user, new UserConverter());
    }

}
