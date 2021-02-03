package im.ggd.scode.dto.converter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;

import im.ggd.scode.security.model.JwtTokenModel;

public class JwtTokenConverter implements Converter<Object, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(Object source) {
        JwtTokenModel tokenModel = (JwtTokenModel ) source;

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
