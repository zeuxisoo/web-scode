package im.ggd.scode.service;

import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.entity.ArticleEntity;

public interface ArticleService {

    public ArticleEntity store(ArticleStoreRequest request);

}
