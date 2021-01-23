package im.ggd.scode.dto;

import lombok.Data;

@Data
public class ErrorDto {

    private final boolean ok;

    private final String message;

}
