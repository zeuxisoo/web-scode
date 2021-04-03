package im.ggd.scode.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.dto.request.ArticleUpdateRequest;
import im.ggd.scode.entity.ArticleEntity;
import im.ggd.scode.repository.ArticleRepository;
import im.ggd.scode.service.ArticleService;
import im.ggd.scode.utils.CurrentUser;

@Service
@Transactional(readOnly = false)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CurrentUser currentUser;

    @Override
    public ArticleEntity store(ArticleStoreRequest request) {
        ArticleEntity article = new ArticleEntity();
        article.setUserId(currentUser.getId());
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        return articleRepository.save(article);
    }

    @Override
    public List<ArticleEntity> all() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Page<ArticleEntity> list(int currentPage, int perPageSize) {
        return articleRepository.findListByOrderByCreatedAtDesc(PageRequest.of(currentPage, perPageSize));
    }

    @Override
    public ArticleEntity findById(Long id) {
        return articleRepository.findById(id).orElseThrow(
            () -> new NoSuchElementException("Cannot found related article by id")
       );
    }

    @Override
    public ArticleEntity updateById(Long id, ArticleUpdateRequest request) {
        ArticleEntity article = this.findById(id);

        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        return articleRepository.save(article);
    }

    @Override
    public ArticleEntity deleteById(Long id) {
        ArticleEntity article = this.findById(id);

        articleRepository.delete(article);

        return article;
    }

}
