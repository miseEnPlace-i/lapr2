package app.exception;

public class AbortedOperationException extends Exception {
    public AbortedOperationException() {
        super();
    }

    public AbortedOperationException(String message) {
        super(message);
    }
}
