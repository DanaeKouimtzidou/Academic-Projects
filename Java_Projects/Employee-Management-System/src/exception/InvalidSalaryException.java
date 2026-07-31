package exception;

public class InvalidSalaryException extends RuntimeException {

    public InvalidSalaryException() {
        super("Salary cannot be negative.");
    }

}