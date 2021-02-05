package im.ggd.scode.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import im.ggd.scode.dto.request.AuthSignInRequest;
import im.ggd.scode.entity.UserEntity;

@Component
public class AuthUtils {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthSignInRequest createAuthSignInRequest(String username, String password) {
        AuthSignInRequest request = new AuthSignInRequest();

        request.setUsername(username);
        request.setPassword(password);

        return request;
    }

    public String fetchToken(UserEntity user) throws Exception {
        AuthSignInRequest request = createAuthSignInRequest(
            user.getUsername(),
            user.getPassword()
        );

        String content = mvc.perform(
                            post("/auth/signin")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                         )
                         .andReturn()
                         .getResponse()
                         .getContentAsString();

        return JsonPath.read(content, "$.data.token");
    }

}
