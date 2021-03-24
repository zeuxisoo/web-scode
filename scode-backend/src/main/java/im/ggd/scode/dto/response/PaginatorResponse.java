package im.ggd.scode.dto.response;

import java.util.HashMap;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Data;

@Data
public class PaginatorResponse<T> {

    private boolean ok;

    private List<Object> data;

    private HashMap<String, Object> meta;

    public PaginatorResponse(Page<T> data, Converter<Page<T>, List<Object>> converter) {
        this.ok   = true;
        this.data = converter.convert(data);
        this.meta = getMetaData(data);
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
        int currentPage = data.getPageable().getPageNumber();

        HashMap<String, String> links = new HashMap<>();

        if (data.hasPrevious()) {
            links.put("prev", ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam("page", currentPage - 1).build().toString());
        }

        if (data.hasNext()) {
            links.put("next", ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam("page", currentPage + 1).build().toString());
        }

        return links;
    }

}
