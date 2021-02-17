package im.ggd.scode.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;

import im.ggd.scode.entity.ArticleEntity;

public class ArticleListConverter implements Converter<List<ArticleEntity>, List<Object>> {

    @Override
    public List<Object> convert(List<ArticleEntity> source) {
        List<Object> articles = source
            .stream()
            .map(article -> new ArticleConverter().convert(article))
            .collect(Collectors.toList());

        return articles;
    }

}
