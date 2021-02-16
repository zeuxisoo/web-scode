package im.ggd.scode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import im.ggd.scode.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    public ArticleEntity findByUserId(Long userId);

}
