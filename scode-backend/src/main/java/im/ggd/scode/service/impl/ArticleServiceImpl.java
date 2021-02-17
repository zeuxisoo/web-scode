package im.ggd.scode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.entity.ArticleEntity;
import im.ggd.scode.repository.ArticleRepository;
import im.ggd.scode.service.ArticleService;
import im.ggd.scode.utils.CurrentUser;

@Service
@Transactional(readOnly = true)
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
    public List<ArticleEntity> show() {
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

}
