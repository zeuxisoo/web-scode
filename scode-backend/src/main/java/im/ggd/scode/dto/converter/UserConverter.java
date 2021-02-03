package im.ggd.scode.dto.converter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;

import im.ggd.scode.entity.UserEntity;

public class UserConverter implements Converter<Object, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(Object source) {
        UserEntity userModel = (UserEntity) source;

        Map<String, Object> user = Stream.of(new Object[][] {
            { "username",   userModel.getUsername() },
            { "email",      userModel.getEmail() },
            { "created_at", userModel.getCreatedAt() },
        }).collect(
            Collectors.toMap(
                data -> (String) data[0],
                data -> (Object) data[1]
            )
        );

        return user;
    }

}
