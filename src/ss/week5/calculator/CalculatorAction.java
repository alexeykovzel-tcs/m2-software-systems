package ss.week5.calculator;

import ss.week5.calculator.exception.DivideByZeroException;
import ss.week5.calculator.exception.StackEmptyException;

import java.util.Arrays;
import java.util.Optional;

public enum CalculatorAction {
    PUSH("push"),
    POP("pop"),
    ADD("add", Calculator::add),
    SUB("sub", Calculator::sub),
    MULT("mult", Calculator::mult),
    DIV("div", Calculator::div);

    private final String call;
    private ArithmeticOperation operation;

    CalculatorAction(String call) {
        this.call = call;
    }

    CalculatorAction(String call, ArithmeticOperation operation) {
        this.call = call;
        this.operation = operation;
    }

    public void execute(Calculator c) throws DivideByZeroException, StackEmptyException {
        operation.calculate(c);
    }

    public static Optional<CalculatorAction> byCall(String s) {
        return Arrays.stream(values()).filter(operation -> operation.call.equals(s)).findFirst();
    }
}

@FunctionalInterface
interface ArithmeticOperation {
    void calculate(Calculator c) throws StackEmptyException, DivideByZeroException;
}
