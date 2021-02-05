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
import im.ggd.scode.utils.UserUtils;

public class CreateTest extends BaseTestCase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserUtils userUtils;

    private UserEntity defaultUser;

    @BeforeEach
    public void setUp() {
        defaultUser = userUtils.createUser("test", "testtest", "test@test.com");
    }

    @Test
    @Order(1)
    public void createUser() throws Exception {
        checkOkMessage(defaultUser, "user created");
    }

    @Test
    @Order(2)
    public void isUsernameExists() throws Exception {
        // original username and password, new email
        UserEntity user = userUtils.createUser(
            defaultUser.getUsername(),
            defaultUser.getPassword(),
            "test2@test2.com"
        );

        checkErrorMessage(user, "Username already exists");
    }

    @Test
    @Order(3)
    public void isEmailExists() throws Exception {
        // new username, original password and email
        UserEntity user = userUtils.createUser(
            "test3",
            defaultUser.getPassword(),
            defaultUser.getEmail()
        );

        checkErrorMessage(user, "Email already exists");
    }

    @Test
    @Order(4)
    public void isUsernameEmpty() throws Exception {
        UserEntity user = userUtils.createUser(
            "",
            "fakePassword",
            "fake@email.com"
        );

        checkErrorMessage(user, "Please enter username");
    }

    @Test
    @Order(5)
    public void isPasswordEmpty() throws Exception {
        UserEntity user = userUtils.createUser(
            "fakeUser",
            "",
            "fake@email.com"
        );

        checkErrorMessage(user, "Please enter password");
    }

    @Test
    @Order(6)
    public void isEmailEmpty() throws Exception {
        UserEntity user = userUtils.createUser(
            "fakeUser",
            "fakePassword",
            ""
        );

        checkErrorMessage(user, "Please enter email");
    }

    @Test
    @Order(7)
    public void isUsernameLessThan4() throws Exception {
        UserEntity user = userUtils.createUser(
            "u",
            "fakePassword",
            "fake@email.com"
        );

        checkErrorMessage(user, "Username must more than 4 letters");
    }

    @Test
    @Order(8)
    public void isPasswordLessThan8() throws Exception {
        UserEntity user = userUtils.createUser(
            "fakeUser",
            "p",
            "fake@email.com"
        );

        checkErrorMessage(user, "Password must more than 8 letters");
    }

    @Test
    @Order(9)
    public void isInvalidEmail() throws Exception {
        UserEntity user = userUtils.createUser(
            "fakeUsername",
            "fakePassword",
            "e"
        );

        checkErrorMessage(user, "Email format incorrect");
    }

    @Test
    @Order(10)
    public void createSecondUser() throws Exception {
        UserEntity user = userUtils.createUser(
            "test4",
            "testtest",
            "test4@test4.com"
        );

        checkOkMessage(user, "user created");
    }

    // Helper
    private void checkOkMessage(UserEntity user, String message) throws Exception {
        mvc.perform(
            post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        )
        // .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ok", is(true)))
        .andExpect(jsonPath("$.data.username", is(user.getUsername())))
        .andExpect(jsonPath("$.data.email", is(user.getEmail())))
        .andExpect(jsonPath("$.data.created_at").isNotEmpty());
    }

    private void checkErrorMessage(UserEntity user, String message) throws Exception {
        mvc.perform(
            post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        )
        // .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.ok", is(false)))
        .andExpect(jsonPath("$.message", is(message)));
    }

}
