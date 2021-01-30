package im.ggd.scode.dto.response;

import java.util.Map;

import im.ggd.scode.dto.transformer.ITransformer;
import lombok.Data;

@Data
public class ItemResponse {

    private boolean ok;

    private Map<String, Object> data;

    public ItemResponse(Object data, ITransformer transformer) {
        this.ok   = true;
        this.data = transformer.transform(data);
    }

}
