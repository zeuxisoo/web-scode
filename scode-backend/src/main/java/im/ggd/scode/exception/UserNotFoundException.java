package im.ggd.scode.exception;

public class UserNotFoundException extends CustomException {

    private static final long serialVersionUID = 5145965988406283378L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
