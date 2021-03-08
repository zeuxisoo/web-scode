package im.ggd.scode.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.stereotype.Component;

import im.ggd.scode.entity.ArticleEntity;

@Component
public class ArticleUtils {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    public ArticleEntity createArticle(String title, String content) {
        ArticleEntity article = new ArticleEntity();

        article.setTitle(title);
        article.setContent(content);

        return article;
    }

    public ResultActions storeArticle(String jwtToken, ArticleEntity article) throws Exception {
        return mvc.perform(
            post("/api/article/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .content(objectMapper.writeValueAsString(article))
        );
    }

}
