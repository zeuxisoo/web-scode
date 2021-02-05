package im.ggd.scode.controller.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import im.ggd.scode.ScodeApplication;
import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.utils.AuthUtils;
import im.ggd.scode.utils.UserUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ScodeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RefreshTest {

    private static boolean isInit = true;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthUtils authUtils;

    private UserEntity defaultUser;

    @BeforeEach
    public void setUp() throws Exception {
        defaultUser = UserUtils.createUser("test", "testtest", "test@test.com");

        if (isInit) {
            // Store to database
            mvc.perform(
                post("/user/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(defaultUser))
            )
            .andExpect(jsonPath("$.ok", is(true)));
        }

        isInit = false;
    }

    @Test
    @Order(1)
    public void refreshToken() throws Exception {
        // Sign in to get the token first
        String token = authUtils.fetchToken(defaultUser);

        mvc.perform(
                get("/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ok", is(true)))
        .andExpect(jsonPath("$.data.username", is(defaultUser.getUsername())))
        .andExpect(jsonPath("$.data.token").isNotEmpty())
        .andExpect(jsonPath("$.data.expired_at").isNotEmpty());
    }

    @Test
    @Order(2)
    public void invalidTokenToRefresh() throws Exception {
        // Sign in to get the token first
        String token = "this_is_a_invalid_token";

        //
        mvc.perform(
                get("/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token))
        )
        .andExpect(status().isForbidden())
        .andExpect(jsonPath("$.ok", is(false)))
        .andExpect(jsonPath("$.message", is("Entry point authentication failed")));
    }

}
