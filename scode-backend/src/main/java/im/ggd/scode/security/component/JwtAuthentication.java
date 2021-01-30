package im.ggd.scode.security.component;

import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import im.ggd.scode.security.model.JwtTokenModel;

@Component
public class JwtAuthentication {

    private byte[] secretKey = "this-is-a-hard-code-key-must-change-it".getBytes();

    private int expiredSecond = 60*60*24*7;

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

}
