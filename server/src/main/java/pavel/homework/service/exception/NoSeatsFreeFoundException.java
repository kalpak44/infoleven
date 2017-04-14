package pavel.homework.service.exception;

/**
 * Created on 014 14.04.17.
 */
public class NoSeatsFreeFoundException extends RuntimeException {
    public NoSeatsFreeFoundException() {
        super("No seats free found");
    }
}
