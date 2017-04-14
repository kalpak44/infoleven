package pavel.homework.service.exception;

/**
 * Created on 014 14.04.17.
 */
public class NoBusFoundException extends RuntimeException {
    public NoBusFoundException() {
        super("No bus found");
    }
}
