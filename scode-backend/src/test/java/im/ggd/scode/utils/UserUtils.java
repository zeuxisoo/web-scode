package im.ggd.scode.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import im.ggd.scode.entity.UserEntity;

@Component
public class UserUtils {

    @Autowired
    private MockMvc mvc;

    public UserEntity createUser(String username, String password, String email) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

    public ResultActions showProfile(String jwtToken) throws Exception {
        return mvc.perform(
            get("/api/user/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", jwtToken))
        );
    }

}
