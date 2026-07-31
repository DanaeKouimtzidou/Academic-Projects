package service;

import java.util.regex.Pattern;

import exception.InvalidAgeException;
import exception.InvalidEmailException;
import exception.InvalidNameException;
import exception.InvalidSalaryException;

public class ValidationService {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public void validateName(String name) throws InvalidNameException {

        if (name == null || name.trim().isEmpty() || name.trim().length() < 2) {
        	throw new InvalidNameException();
        }

    }

    public void validateEmail(String email) throws InvalidEmailException {

        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
        	throw new InvalidEmailException();
        }

    }

    public void validateAge(int age) throws InvalidAgeException {

        if (age < 18 || age > 67) {
        	throw new InvalidAgeException();
        }

    }

    public void validateSalary(double salary) throws InvalidSalaryException {

        if (salary <= 0) {
        	throw new InvalidSalaryException();
        }

    }

}