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

import im.ggd.scode.annotation.ApiController;
import im.ggd.scode.dto.converter.JwtTokenConverter;
import im.ggd.scode.dto.request.AuthSignInRequest;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.security.model.JwtTokenModel;
import im.ggd.scode.service.AuthService;
import im.ggd.scode.validation.group.order.BasicOrder;

@ApiController
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    private ItemResponse<JwtTokenModel> itemResponse;

    @PostMapping("/signin")
    public ItemResponse<?> signIn(@RequestBody @Validated({ BasicOrder.class }) AuthSignInRequest request) {
        JwtTokenModel token = authService.signIn(request);

        return itemResponse.ok(token, new JwtTokenConverter());
    }

    @GetMapping("/refresh")
    public ItemResponse<?> refresh(@RequestHeader HttpHeaders headers) {
        String authorization = headers.getFirst(HttpHeaders.AUTHORIZATION);

        JwtTokenModel token = authService.refresh(authorization);

        return itemResponse.ok(token, new JwtTokenConverter());
    }

}
