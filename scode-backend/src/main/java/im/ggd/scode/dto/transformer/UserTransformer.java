package im.ggd.scode.dto.transformer;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import im.ggd.scode.entity.UserEntity;

public class UserTransformer implements ITransformer {

    @Override
    public Map<String, Object> transform(Object object) {
        UserEntity userModel = (UserEntity) object;

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
