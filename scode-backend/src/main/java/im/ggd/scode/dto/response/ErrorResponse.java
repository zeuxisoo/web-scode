package im.ggd.scode.dto.response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ErrorResponse {

    private boolean ok;

    private String message;

    public ErrorResponse error(String message) {
        this.ok      = false;
        this.message = message;

        return this;
    }

}
