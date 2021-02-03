package im.ggd.scode.dto.response;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;

import lombok.Data;

@Data
public class ItemResponse {

    private boolean ok;

    private Map<String, Object> data;

    public ItemResponse(Object data, Converter<Object, Map<String, Object>> converter) {
        this.ok   = true;
        this.data = converter.convert(data);
    }

}
