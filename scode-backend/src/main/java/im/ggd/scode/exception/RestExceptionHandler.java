package im.ggd.scode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import im.ggd.scode.dto.response.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> methodArgumentNotValid(MethodArgumentNotValidException e) {
        String firstErrorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = createErrorResponse(status, firstErrorMessage);

        return new ResponseEntity<Object>(errorResponse, status);
    }

    //
    protected ErrorResponse createErrorResponse(HttpStatus status, String message) {
        boolean isOK = status.value() == 200;

        return new ErrorResponse(isOK, message);
    }

}
