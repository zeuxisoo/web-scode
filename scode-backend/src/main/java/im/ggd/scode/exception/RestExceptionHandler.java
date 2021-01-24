package im.ggd.scode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import im.ggd.scode.dto.response.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ErrorResponse methodArgumentNotValid(MethodArgumentNotValidException e) {
        String firstErrorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        ErrorResponse errorResponse = createErrorResponse(false, firstErrorMessage);

        return errorResponse;
    }

    //
    protected ErrorResponse createErrorResponse(boolean status, String message) {
        return new ErrorResponse(status, message);
    }

}
