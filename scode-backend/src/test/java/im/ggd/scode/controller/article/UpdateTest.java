package im.ggd.scode.controller.article;

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
import im.ggd.scode.entity.ArticleEntity;
import im.ggd.scode.entity.UserEntity;
import im.ggd.scode.utils.ArticleUtils;
import im.ggd.scode.utils.AuthUtils;
import im.ggd.scode.utils.UserUtils;

public class UpdateTest extends BaseTestCase {

    private static boolean isInit = true;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private ArticleUtils articleUtils;

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

        // Create articles
        if (isInit) {
            ArticleEntity article1 = articleUtils.createArticle("title1", "content1");
            ArticleEntity article2 = articleUtils.createArticle("title2", "content2");
            ArticleEntity article3 = articleUtils.createArticle("title3", "content3");
            ArticleEntity article4 = articleUtils.createArticle("title4", "content4");
            ArticleEntity article5 = articleUtils.createArticle("title5", "content5");

            articleUtils.storeArticle(jwtToken, article1);
            articleUtils.storeArticle(jwtToken, article2);
            articleUtils.storeArticle(jwtToken, article3);
            articleUtils.storeArticle(jwtToken, article4);
            articleUtils.storeArticle(jwtToken, article5);
        }

        isInit = false;
    }

    @Test
    @Order(1)
    public void IsArticle1UpdateOK() throws Exception {
        ArticleEntity newArticle = articleUtils.createArticle("updateTitle1", "updateContent1");

        articleUtils.updateArticle(jwtToken, 1L, newArticle)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.title").value("updateTitle1"))
            .andExpect(jsonPath("$.data.content").value("updateContent1"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    @Order(2)
    public void IsArticle3UpdateOK() throws Exception {
        ArticleEntity newArticle = articleUtils.createArticle("updateTitle3", "updateContent3");

        articleUtils.updateArticle(jwtToken, 3L, newArticle)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.id").value(3))
            .andExpect(jsonPath("$.data.title").value("updateTitle3"))
            .andExpect(jsonPath("$.data.content").value("updateContent3"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    @Order(3)
    public void IsArticle1ShowOKAfterUpdated() throws Exception {
        articleUtils.showArticle(jwtToken, 1L)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.title").value("updateTitle1"))
            .andExpect(jsonPath("$.data.content").value("updateContent1"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    @Order(4)
    public void IsArticle3ShowOKAfterUpdated() throws Exception {
        articleUtils.showArticle(jwtToken, 3L)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.title").value("updateTitle3"))
            .andExpect(jsonPath("$.data.content").value("updateContent3"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    @Order(5)
    public void IsArticleUpdateInvalidIdMessage() throws Exception {
        ArticleEntity newArticle = articleUtils.createArticle("invalidTitle", "invalidContent");

        articleUtils.updateArticle(jwtToken, "a", newArticle)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.ok", is(false)))
            .andExpect(jsonPath("$.message").value("The type of argument incorrect"));
    }

    @Test
    @Order(6)
    public void IsArticleUpdateIdNotFoundMessage() throws Exception {
        ArticleEntity newArticle = articleUtils.createArticle("invalidTitle", "invalidContent");

        articleUtils.updateArticle(jwtToken, 99999L, newArticle)
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.ok", is(false)))
            .andExpect(jsonPath("$.message").value("Cannot found related article by id"));
    }

}
