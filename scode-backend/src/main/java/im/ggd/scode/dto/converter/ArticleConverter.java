package im.ggd.scode.dto.converter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;

import im.ggd.scode.entity.ArticleEntity;

public class ArticleConverter implements Converter<Object, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(Object source) {
        ArticleEntity articleModel = (ArticleEntity) source;

        Map<String, Object> article = Stream.of(new Object[][] {
            { "id",         articleModel.getId() },
            { "title",      articleModel.getTitle() },
            { "content",    articleModel.getContent() },
            { "created_at", articleModel.getCreatedAt() },
        }).collect(
            Collectors.toMap(
                data -> (String) data[0],
                data -> (Object) data[1]
            )
        );

        return article;
    }

}
