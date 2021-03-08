package im.ggd.scode.dto.response;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PaginatorResponse<T> {

    private boolean ok;

    private List<Object> data;

    public PaginatorResponse(Page<T> data, Converter<Page<T>, List<Object>> converter) {
        this.ok   = true;
        this.data = converter.convert(data);
    }

}
