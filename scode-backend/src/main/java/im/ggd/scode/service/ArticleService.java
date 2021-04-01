package im.ggd.scode.service;

import java.util.List;

import org.springframework.data.domain.Page;

import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.dto.request.ArticleUpdateRequest;
import im.ggd.scode.entity.ArticleEntity;

public interface ArticleService {

    public ArticleEntity store(ArticleStoreRequest request);

    public List<ArticleEntity> all();

    public Page<ArticleEntity> list(int currentPage, int perPageSize);

    public ArticleEntity findById(Long id);

    public ArticleEntity updateById(Long id, ArticleUpdateRequest request);

}
