package im.ggd.scode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import im.ggd.scode.dto.ErrorDto;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        String firstErrorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();

        ErrorDto errorResponse = createErrorResponse(status, firstErrorMessage);

        return new ResponseEntity<Object>(errorResponse, status);
    }

    //
    protected ErrorDto createErrorResponse(HttpStatus status, String message) {
        boolean isOK = status.value() == 200;

        return new ErrorDto(isOK, message);
    }

}
