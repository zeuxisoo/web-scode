package im.ggd.scode.utils;

import org.springframework.stereotype.Component;

import im.ggd.scode.entity.ArticleEntity;

@Component
public class ArticleUtils {

    public ArticleEntity createArticle(String title, String content) {
        ArticleEntity article = new ArticleEntity();

        article.setTitle(title);
        article.setContent(content);

        return article;
    }

}
