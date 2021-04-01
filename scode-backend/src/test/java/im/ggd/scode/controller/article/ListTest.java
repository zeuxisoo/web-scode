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

public class ListTest extends BaseTestCase {

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
            ArticleEntity article6 = articleUtils.createArticle("title6", "content6");
            ArticleEntity article7 = articleUtils.createArticle("title7", "content7");
            ArticleEntity article8 = articleUtils.createArticle("title8", "content8");
            ArticleEntity article9 = articleUtils.createArticle("title9", "content9");
            ArticleEntity article10 = articleUtils.createArticle("title10", "content10");
            ArticleEntity article11 = articleUtils.createArticle("title11", "content11");

            articleUtils.storeArticle(jwtToken, article1);
            articleUtils.storeArticle(jwtToken, article2);
            articleUtils.storeArticle(jwtToken, article3);
            articleUtils.storeArticle(jwtToken, article4);
            articleUtils.storeArticle(jwtToken, article5);
            articleUtils.storeArticle(jwtToken, article6);
            articleUtils.storeArticle(jwtToken, article7);
            articleUtils.storeArticle(jwtToken, article8);
            articleUtils.storeArticle(jwtToken, article9);
            articleUtils.storeArticle(jwtToken, article10);
            articleUtils.storeArticle(jwtToken, article11);
        }

        isInit = false;
    }

    @Test
    @Order(1)
    public void ListArticleListInPage0() throws Exception {
        articleUtils.listArticle(jwtToken, 0)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data", hasSize(8)))
            .andExpect(jsonPath("$.meta.pagination.per_page", is(8)))
            .andExpect(jsonPath("$.meta.pagination.total", is(11)))
            .andExpect(jsonPath("$.meta.pagination.count", is(8)))
            .andExpect(jsonPath("$.meta.pagination.total_pages", is(2)))
            .andExpect(jsonPath("$.meta.pagination.current_page", is(0)))
            .andExpect(jsonPath("$.meta.pagination.links.next").exists());
    }

    @Test
    @Order(2)
    public void ListArticleListInPage1() throws Exception {
        articleUtils.listArticle(jwtToken, 1)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ok", is(true)))
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data", hasSize(3)))
            .andExpect(jsonPath("$.meta.pagination.per_page", is(8)))
            .andExpect(jsonPath("$.meta.pagination.total", is(11)))
            .andExpect(jsonPath("$.meta.pagination.count", is(3)))
            .andExpect(jsonPath("$.meta.pagination.total_pages", is(2)))
            .andExpect(jsonPath("$.meta.pagination.current_page", is(1)))
            .andExpect(jsonPath("$.meta.pagination.links.prev").exists());
    }

}
