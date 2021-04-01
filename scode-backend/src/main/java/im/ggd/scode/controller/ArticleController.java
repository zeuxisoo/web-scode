package im.ggd.scode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.annotation.ApiController;
import im.ggd.scode.dto.converter.ArticleConverter;
import im.ggd.scode.dto.converter.ArticleListConverter;
import im.ggd.scode.dto.converter.ArticleAllConverter;
import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.dto.request.ArticleUpdateRequest;
import im.ggd.scode.dto.response.CollectionResponse;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.dto.response.PaginatorResponse;
import im.ggd.scode.entity.ArticleEntity;
import im.ggd.scode.service.ArticleService;
import im.ggd.scode.validation.group.order.BasicOrder;

@ApiController
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ItemResponse<ArticleEntity> itemResponse;

    @Autowired
    private CollectionResponse<ArticleEntity> collectionResponse;

    @Autowired
    private PaginatorResponse<ArticleEntity> paginatorResponse;

    @Value("${app.article.list.per-page:10}")
    private int perPageSize;

    @PostMapping("/create")
    public ItemResponse<?> create(@RequestBody @Validated({ BasicOrder.class }) ArticleStoreRequest request) {
        ArticleEntity article = articleService.store(request);

        return itemResponse.ok(article, new ArticleConverter());
    }

    @GetMapping("/all")
    public CollectionResponse<?> all() {
        List<ArticleEntity> articles = articleService.all();

        return collectionResponse.ok(articles, new ArticleAllConverter());
    }

    @GetMapping("/list")
    public PaginatorResponse<?> list(@RequestParam(required = false, defaultValue = "0") int page) {
        Page<ArticleEntity> articles = articleService.list(page, perPageSize);

        return paginatorResponse.ok(articles, new ArticleListConverter());
    }

    @GetMapping("/show/{id}")
    public ItemResponse<?> show(@PathVariable(required = true) Long id) {
        ArticleEntity article = articleService.findById(id);

         return itemResponse.ok(article, new ArticleConverter());
    }

    @PostMapping("/update/{id}")
    public ItemResponse<?> update(
        @PathVariable(required = true) Long id,
        @RequestBody @Validated({ BasicOrder.class }) ArticleUpdateRequest request
    ) {
        ArticleEntity article = articleService.updateById(id, request);

        return itemResponse.ok(article, new ArticleConverter());
    }

}
