package im.ggd.scode.security.model;

import lombok.Data;

@Data
public class JwtTokenModel {

    private final String token;

    private final long expired_at;

}
