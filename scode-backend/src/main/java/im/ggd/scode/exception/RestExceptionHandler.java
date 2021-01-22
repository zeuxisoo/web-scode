package im.ggd.scode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import im.ggd.scode.transformer.ErrorTransformer;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        String firstErrorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ErrorTransformer errorResponse = createErrorResponse(status, firstErrorMessage);

        return new ResponseEntity<Object>(errorResponse, status);
    }

    //
    protected ErrorTransformer createErrorResponse(HttpStatus status, String message) {
        boolean isOK = status.value() == 200;

        return new ErrorTransformer(isOK, message);
    }

}
