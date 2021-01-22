package im.ggd.scode.transformer;

import lombok.Data;

@Data
public class ErrorTransformer {

    private final boolean ok;

    private final String message;

}
