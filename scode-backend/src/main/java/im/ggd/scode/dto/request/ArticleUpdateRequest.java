package im.ggd.scode.dto.request;

import javax.validation.constraints.NotEmpty;

import im.ggd.scode.validation.group.EmptyGroup;
import lombok.Data;

@Data
public class ArticleUpdateRequest {

    @NotEmpty(message = "Please enter title", groups = EmptyGroup.class)
    private String title;

    @NotEmpty(message = "Please enter content", groups = EmptyGroup.class)
    private String content;

}
