package im.ggd.scode.dto.transformer;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import im.ggd.scode.model.UserModel;

public class UserTransformer implements ITransformer {

    public Map<String, Object> transform(Object object) {
        UserModel userModel = (UserModel) object;

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
