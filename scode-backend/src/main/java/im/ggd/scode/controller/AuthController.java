package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.dto.request.SignInUserRequest;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.dto.transformer.JwtTokenTransformer;
import im.ggd.scode.security.model.JwtTokenModel;
import im.ggd.scode.service.UserService;
import im.ggd.scode.validation.group.order.BasicOrder;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ItemResponse signIn(@RequestBody @Validated({ BasicOrder.class }) SignInUserRequest request) {
        JwtTokenModel token = userService.signIn(request);

        return new ItemResponse(token, new JwtTokenTransformer());
    }

    @GetMapping("/refresh")
    public ItemResponse refresh(@RequestHeader HttpHeaders headers) {
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);

        JwtTokenModel token = userService.refresh(authorization);

        return new ItemResponse(token, new JwtTokenTransformer());
    }

}
