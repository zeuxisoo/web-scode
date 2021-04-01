package im.ggd.scode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.entity.ArticleEntity;

public interface ArticleService {

    public ArticleEntity store(ArticleStoreRequest request);

    public List<ArticleEntity> all();

    public Page<ArticleEntity> list(int currentPage, int perPageSize);

    public Optional<ArticleEntity> findById(Long id);

}
