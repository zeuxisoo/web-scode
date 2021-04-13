package im.ggd.scode.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import im.ggd.scode.BaseTestCase;
import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.utils.AuthUtils;
import im.ggd.scode.utils.UserUtils;

public class ProfileTest extends BaseTestCase {

    private static boolean isInit = true;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private AuthUtils authUtils;

    private UserEntity defaultUser;

    private String jwtToken;

    @BeforeEach
    public void setUp() throws Exception {
        defaultUser = userUtils.createUser("test", "testtest", "test@test.com");

        if (isInit) {
            // Store to database
            mvc.perform(
                post("/api/user/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(defaultUser))
            )
            .andExpect(jsonPath("$.ok", is(true)));
        }

        // Generate jwt token on each request
        jwtToken = authUtils.fetchToken(defaultUser);

        isInit = false;
    }

    @Test
    @Order(1)
    public void IsProfileShowOK() throws Exception {
        userUtils.showProfile(jwtToken)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.username").value("test"))
            .andExpect(jsonPath("$.data.email").value("test@test.com"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

}
