package exception;

public class InvalidAgeException extends RuntimeException {

    public InvalidAgeException() {
        super("Invalid employee age.");
    }

}