package im.ggd.scode.service;

import im.ggd.scode.dto.request.AuthSignInRequest;
import im.ggd.scode.security.model.JwtTokenModel;

public interface AuthService {

    public JwtTokenModel signIn(AuthSignInRequest request);

    public JwtTokenModel refresh(String authorization);

}
