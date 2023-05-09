package ss.week5.calculator.exception;

import ss.week5.calculator.Calculator;

/**
 * Exception thrown by the {@link Calculator} when trying to divide by zero.
 */
public class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}
