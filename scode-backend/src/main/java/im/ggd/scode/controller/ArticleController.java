package im.ggd.scode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.annotation.ApiController;
import im.ggd.scode.dto.converter.ArticleConverter;
import im.ggd.scode.dto.request.ArticleStoreRequest;
import im.ggd.scode.dto.response.ItemResponse;
import im.ggd.scode.entity.ArticleEntity;
import im.ggd.scode.service.ArticleService;
import im.ggd.scode.utils.CurrentUser;
import im.ggd.scode.validation.group.order.BasicOrder;

@ApiController
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CurrentUser currentUser;

    @PostMapping("/create")
    public ItemResponse create(@RequestBody @Validated({ BasicOrder.class }) ArticleStoreRequest request) {
        ArticleEntity article = articleService.store(request);

        return new ItemResponse(article, new ArticleConverter());
    }

}
