package im.ggd.scode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import im.ggd.scode.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    public ArticleEntity findByUserId(Long userId);

    public List<ArticleEntity> findAllByOrderByCreatedAtDesc();

    public Page<ArticleEntity> findListByOrderByCreatedAtDesc(Pageable pageable);

}
