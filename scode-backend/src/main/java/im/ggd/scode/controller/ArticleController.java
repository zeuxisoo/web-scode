package im.ggd.scode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.annotation.ApiController;
import im.ggd.scode.dto.converter.ArticleConverter;
import im.ggd.scode.dto.converter.ArticleListConverter;
import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.dto.response.CollectionResponse;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.entity.ArticleEntity;
import im.ggd.scode.service.ArticleService;
import im.ggd.scode.validation.group.order.BasicOrder;

@ApiController
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ItemResponse create(@RequestBody @Validated({ BasicOrder.class }) ArticleStoreRequest request) {
        ArticleEntity article = articleService.store(request);

        return new ItemResponse(article, new ArticleConverter());
    }

    @GetMapping("/all")
    public CollectionResponse<?> all() {
        List<ArticleEntity> articles = articleService.all();

        return new CollectionResponse<>(articles, new ArticleListConverter());
    }

}
