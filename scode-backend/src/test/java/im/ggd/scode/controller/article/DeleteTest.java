package im.ggd.scode.controller.article;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

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

public class DeleteTest extends BaseTestCase {

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
    public void IsArticle1DeleteOK() throws Exception {
        articleUtils.deleteArticle(jwtToken, 1L)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.title").value("title1"))
            .andExpect(jsonPath("$.data.content").value("content1"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    @Order(2)
    public void IsArticleListHas4SizeAfterDeleted1() throws Exception {
        articleUtils.allArticle(jwtToken)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data", hasSize(4)));
    }

    @Test
    @Order(3)
    public void IsArticle3DeleteOK() throws Exception {
        articleUtils.deleteArticle(jwtToken, 3L)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data.id").value(3))
            .andExpect(jsonPath("$.data.title").value("title3"))
            .andExpect(jsonPath("$.data.content").value("content3"))
            .andExpect(jsonPath("$.data.created_at").exists());
    }

    @Test
    @Order(4)
    public void IsArticleListHas3SizeAfterDeleted3() throws Exception {
        articleUtils.allArticle(jwtToken)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    @Order(5)
    public void IsArticleDeleteInvalidIdMessage() throws Exception {
        articleUtils.deleteArticle(jwtToken, "a")
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.ok", is(false)))
            .andExpect(jsonPath("$.message").value("The type of argument incorrect"));
    }

    @Test
    @Order(6)
    public void IsArticleDeleteIdNotFoundMessage() throws Exception {
        articleUtils.deleteArticle(jwtToken, 99999L)
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.ok", is(false)))
            .andExpect(jsonPath("$.message").value("Cannot found related article by id"));
    }

}
