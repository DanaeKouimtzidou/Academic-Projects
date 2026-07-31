package exception;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
        super("Invalid employee name.");
    }

}