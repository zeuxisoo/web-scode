package im.ggd.scode.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import im.ggd.scode.ScodeApplication;
import im.ggd.scode.entity.UserEntity;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ScodeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity defaultUser;

    @BeforeEach
    public void setUp() {
        defaultUser = createUser("test", "testtest", "test@test.com");
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
        UserEntity user = createUser(
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
        UserEntity user = createUser(
            "test3",
            defaultUser.getPassword(),
            defaultUser.getEmail()
        );

        checkErrorMessage(user, "Email already exists");
    }

    @Test
    @Order(4)
    public void isUsernameEmpty() throws Exception {
        UserEntity user = createUser(
            "",
            "fakePassword",
            "fake@email.com"
        );

        checkErrorMessage(user, "Please enter username");
    }

    @Test
    @Order(5)
    public void isPasswordEmpty() throws Exception {
        UserEntity user = createUser(
            "fakeUser",
            "",
            "fake@email.com"
        );

        checkErrorMessage(user, "Please enter password");
    }

    @Test
    @Order(6)
    public void isEmailEmpty() throws Exception {
        UserEntity user = createUser(
            "fakeUser",
            "fakePassword",
            ""
        );

        checkErrorMessage(user, "Please enter email");
    }

    @Test
    @Order(7)
    public void isUsernameLessThan4() throws Exception {
        UserEntity user = createUser(
            "u",
            "fakePassword",
            "fake@email.com"
        );

        checkErrorMessage(user, "Username must more than 4 letters");
    }

    @Test
    @Order(8)
    public void isPasswordLessThan8() throws Exception {
        UserEntity user = createUser(
            "fakeUser",
            "p",
            "fake@email.com"
        );

        checkErrorMessage(user, "Password must more than 8 letters");
    }

    @Test
    @Order(9)
    public void isInvalidEmail() throws Exception {
        UserEntity user = createUser(
            "fakeUsername",
            "fakePassword",
            "e"
        );

        checkErrorMessage(user, "Email format incorrect");
    }

    @Test
    @Order(10)
    public void createSecondUser() throws Exception {
        UserEntity user = createUser(
            "test4",
            "testtest",
            "test4@test4.com"
        );

        checkOkMessage(user, "user created");
    }

    // Helper
    private UserEntity createUser(String username, String password, String email) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }

    private void checkOkMessage(UserEntity user, String message) throws Exception {
        mvc.perform(
            post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        )
        // .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(message));
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
