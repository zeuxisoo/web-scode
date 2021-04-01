package im.ggd.scode.dto.response;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ItemResponse<T> {

    private boolean ok;

    private Map<String, Object> data;

    public ItemResponse<T> ok(Object data, Converter<Object, Map<String, Object>> converter) {
        this.ok   = true;
        this.data = converter.convert(data);

        return this;
    }

}
