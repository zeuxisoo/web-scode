package im.ggd.scode.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import im.ggd.scode.dto.response.ErrorResponse;

@RestController
public class ErrorController extends AbstractErrorController {

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes, Collections.emptyList());
    }

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, ErrorAttributeOptions.of(
            ErrorAttributeOptions.Include.EXCEPTION,
            ErrorAttributeOptions.Include.BINDING_ERRORS,
            ErrorAttributeOptions.Include.STACK_TRACE,
            ErrorAttributeOptions.Include.MESSAGE
        ));

        String error = errorAttributes.get("error").toString();
        String message = errorAttributes.get("message").toString();
        String path = errorAttributes.get("path").toString();
        String status = errorAttributes.get("status").toString();

        String body;
        switch(status) {
            case "404":
                body = String.format("The path %s not found", path);
                break;
            default:
                body = String.format("%s %s at %s", status, error, path, message);
                break;
        }

        ErrorResponse response = new ErrorResponse(false, body);

        return ResponseEntity.status(getStatus(request)).body(response);
    }

}
