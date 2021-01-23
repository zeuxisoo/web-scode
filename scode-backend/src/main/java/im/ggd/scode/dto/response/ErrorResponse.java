package im.ggd.scode.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private final boolean ok;

    private final String message;

}
