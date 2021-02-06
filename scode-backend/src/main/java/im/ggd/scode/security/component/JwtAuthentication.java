package im.ggd.scode.security.component;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import im.ggd.scode.security.model.JwtTokenModel;

@Component
public class JwtAuthentication {

    @Value("${security.jwt.token.secret-key:ThisIsDefaultSecretKey}.getBytes()")
    private byte[] secretKey;

    // Default 7 days for first token when related config is null
    @Value("#{ ${security.jwt.token.expired-second:60*60*24*7} }")
    private int expiredSecond;

    // Default 14 days for refresh token when related config is null
    @Value("#{ ${security.jwt.token.refresh-second:60*60*24*7*2} }")
    private int refreshSecond;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtTokenModel createToken(String username) {
        // Create new claims and set the username to subject
        Claims newClaims = Jwts.claims();
        newClaims.setSubject(username);

        return generateToken(newClaims, expiredSecond);
    }

    public String getToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        return getTokenFromAuthorization(authorization);
    }

    public String getTokenFromAuthorization(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer")) {
            return authorization.replace("Bearer ", "");
        }

        return null;
    }

    public boolean verifyToken(String token) {
        if (token == null) return false;

        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        }catch(JwtException e) {
            return false;
        }

        return true;
    }

    public Claims getClaims(String token) {
        // Throw exception when token is expired or invalid in parse failed
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        Claims claims = parser.parseClaimsJws(token).getBody();

        return claims;
    }

    public Authentication getAuthentication(Claims claims) {
        String username = claims.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public JwtTokenModel refreshToken(String token) {
        Claims oldClaims = getClaims(token);

        return generateToken(oldClaims, refreshSecond);
    }

    //
    private JwtTokenModel generateToken(Claims claims, int expireSecond) {
        // Current date
        Date now = new Date();

        // Expire date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expireSecond);

        // Set the claims issue date and expire date
        claims.setIssuedAt(now);
        claims.setExpiration(calendar.getTime());

        // Build token
        String token = Jwts.builder()
            .setClaims(claims)
            .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
            .compact();

        return new JwtTokenModel(claims.getSubject(), token, calendar.getTimeInMillis());
    }

}
