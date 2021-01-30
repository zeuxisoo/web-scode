package im.ggd.scode.dto.transformer;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import im.ggd.scode.security.model.JwtTokenModel;

public class JwtTokenTransformer implements ITransformer {

    @Override
    public Map<String, Object> transform(Object object) {
        JwtTokenModel tokenModel = (JwtTokenModel ) object;

        Map<String, Object> user = Stream.of(new Object[][] {
            { "username",   tokenModel.getUsername() },
            { "token",      tokenModel.getToken() },
            { "expired_at", tokenModel.getExpiredAt() },
        }).collect(
            Collectors.toMap(
                data -> (String) data[0],
                data -> (Object) data[1]
            )
        );

        return user;
    }

}
