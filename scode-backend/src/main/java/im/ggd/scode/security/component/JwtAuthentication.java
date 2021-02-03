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
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import im.ggd.scode.security.model.JwtTokenModel;

@Component
public class JwtAuthentication {

    private byte[] secretKey = "this-is-a-hard-code-key-must-change-it".getBytes();

    private int expiredSecond = 60*60*24*7;

    private int refreshSecond = 60*60*24*7*2;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtTokenModel createToken(String username) {
        // Claims
        Claims claims = Jwts.claims();
        claims.setSubject(username);

        // Current date
        Date now = new Date();

        // Expire at 7 days
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expiredSecond);

        // Build token
        String token = Jwts.builder()
            .setClaims(claims)
            .setIssuer("sCode")
            .setIssuedAt(now)
            .setExpiration(calendar.getTime())
            .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
            .compact();

        return new JwtTokenModel(username, token, calendar.getTimeInMillis());
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
        // Current date
        Date now = new Date();

        // Refresh at 7 * 2 days
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, refreshSecond);

        // Change expiration date and issue date base on old claim
        Claims claims = getClaims(token);
        claims.setIssuedAt(now);
        claims.setExpiration(calendar.getTime());

        // Build refreshed token
        String refreshToken = Jwts.builder()
            .setClaims(claims)
            .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
            .compact();

        return new JwtTokenModel(claims.getSubject(), refreshToken, calendar.getTimeInMillis());
    }

}
