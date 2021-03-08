package im.ggd.scode.service;

import java.util.List;

import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.entity.ArticleEntity;

public interface ArticleService {

    public ArticleEntity store(ArticleStoreRequest request);

    public List<ArticleEntity> all();

}
