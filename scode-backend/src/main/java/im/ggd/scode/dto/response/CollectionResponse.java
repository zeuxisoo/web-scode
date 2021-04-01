package im.ggd.scode.dto.response;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CollectionResponse<T> {

    private boolean ok;

    private List<Object> data;

    public CollectionResponse<T> ok(List<T> data, Converter<List<T>, List<Object>> converter) {
        this.ok   = true;
        this.data = converter.convert(data);

        return this;
    }

}
