package im.ggd.scode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import im.ggd.scode.dto.request.AuthSignInRequest;
import im.ggd.scode.security.component.JwtAuthentication;
import im.ggd.scode.security.model.JwtTokenModel;
import im.ggd.scode.service.AuthService;

@Service
@Transactional(readOnly = false)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtAuthentication jwtAuthentication;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JwtTokenModel signIn(AuthSignInRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Authenticate username and password
        // - Throw AuthenticationException when invalid username or password
        Authentication authentication;

        authentication = new UsernamePasswordAuthenticationToken(username, password);
        authentication = authenticationManager.authenticate(authentication);

        // Get authenticated user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generate jwt token
        JwtTokenModel jwtTokenModel = jwtAuthentication.createToken(userDetails.getUsername());

        return jwtTokenModel;
    }

    @Override
    public JwtTokenModel refresh(String authorization) {
        String token = jwtAuthentication.getTokenFromAuthorization(authorization);

        JwtTokenModel jwtTokenModel = jwtAuthentication.refreshToken(token);

        return jwtTokenModel;
    }


}
