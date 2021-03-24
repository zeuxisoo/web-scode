package im.ggd.scode.dto.response;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
public class PaginatorResponse<T> {

    private boolean ok;

    private List<Object> data;

    private HashMap<String, Object> meta;

    @Autowired
    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    private SpringDataWebProperties dataWebProperties;

    public PaginatorResponse<T> ok(Page<T> data, Converter<Page<T>, List<Object>> converter) {
        this.ok   = true;
        this.data = converter.convert(data);
        this.meta = getMetaData(data);

        return this;
    }

    protected HashMap<String, Object> getMetaData(Page<T> data) {
        HashMap<String, Object> meta = new HashMap<>();

        meta.put("pagination", getPagination(data));

        return meta;
    }

    protected HashMap<String, Object> getPagination(Page<T> data) {
        HashMap<String, Object> pagination = new HashMap<>();

        pagination.put("count", data.getNumberOfElements());
        pagination.put("current_page", data.getPageable().getPageNumber());
        pagination.put("per_page", data.getPageable().getPageSize());
        pagination.put("total", data.getTotalElements());
        pagination.put("total_pages", data.getTotalPages());

        pagination.put("links", getPaginationLinks(data));

        return pagination;
    }

    protected HashMap<String, String> getPaginationLinks(Page<T> data) {
        String pageParameterName = dataWebProperties.getPageable().getPageParameter();

        int currentPage = data.getPageable().getPageNumber();

        HashMap<String, String> links = new HashMap<>();

        String name = "";
        String url  = "";
        ServletUriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();

        if (data.hasPrevious()) {
            name = "prev";
            url  = uriBuilder.replaceQueryParam(pageParameterName, currentPage - 1).build().toString();
        }

        if (data.hasNext()) {
            name = "next";
            url  = uriBuilder.replaceQueryParam(pageParameterName, currentPage + 1).build().toString();
        }

        links.put(name, url);

        return links;
    }

}
