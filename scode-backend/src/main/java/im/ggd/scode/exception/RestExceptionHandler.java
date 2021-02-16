package im.ggd.scode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import im.ggd.scode.dto.response.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String firstErrorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        ErrorResponse errorResponse = createErrorResponse(false, firstErrorMessage);

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return createErrorResponse(false, "No content found in the request body");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ AuthenticationException.class })
    public ErrorResponse handleAuthenticationException(AuthenticationException e) {
        return createErrorResponse(false, "Invalid username / password");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ UsernameNotFoundException.class })
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
        return createErrorResponse(false, "Cannot found user by username");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ UserNotFoundException.class })
    public ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        return createErrorResponse(false, "User not found in current status");
    }

    //
    protected ErrorResponse createErrorResponse(boolean isOK, String message) {
        return new ErrorResponse(isOK, message);
    }

}
