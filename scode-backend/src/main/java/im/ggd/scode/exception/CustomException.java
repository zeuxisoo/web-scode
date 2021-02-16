package im.ggd.scode.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -6821441886989439015L;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

}
