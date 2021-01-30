package im.ggd.scode.security.model;

import lombok.Data;

@Data
public class JwtTokenModel {

    private final String username;

    private final String token;

    private final long expiredAt;

}
