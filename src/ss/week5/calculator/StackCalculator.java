package ss.week5.calculator;

import ss.week5.calculator.exception.DivideByZeroException;
import ss.week5.calculator.exception.StackEmptyException;

import java.util.Stack;

public class StackCalculator implements Calculator {
    private final Stack<Double> stack;

    public StackCalculator() {
        stack = new Stack<>();
    }

    @Override
    public void push(double value) {
        stack.push(value);
    }

    @Override
    public double pop() throws StackEmptyException {
        if (stack.isEmpty()) throw new StackEmptyException("Not enough elements in a stack");
        return stack.pop();
    }

    @Override
    public void add() throws StackEmptyException {
        if (stack.size() < 2) throw new StackEmptyException("Not enough elements in a stack");
        stack.push(stack.pop() + stack.pop());
    }

    @Override
    public void sub() throws StackEmptyException {
        if (stack.size() < 2) throw new StackEmptyException("Not enough elements in a stack");
        stack.push(-stack.pop() + stack.pop());
    }

    @Override
    public void mult() throws StackEmptyException {
        if (stack.size() < 2) throw new StackEmptyException("Not enough elements in a stack");
        stack.push(stack.pop() * stack.pop());
    }

    @Override
    public void div() throws DivideByZeroException, StackEmptyException {
        if (stack.size() < 2) throw new StackEmptyException("Not enough elements in a stack");
        double divisor = stack.pop();
        double divider = stack.pop();

        if (divisor == 0) {
            stack.push(Double.NaN);
            throw new DivideByZeroException("Division by 0 is not allowed");
        }
        stack.push(divider / divisor);
    }

    @Override
    public void mod() throws DivideByZeroException, StackEmptyException {
        double divisor = stack.pop();
        double divider = stack.pop();

        if (divisor == 0) {
            stack.push(Double.NaN);
            throw new DivideByZeroException("Division by 0 is not allowed");
        }
        stack.push(divider % divisor);
    }

    @Override
    public void dub() throws StackEmptyException {
        if (stack.size() == 0) {
            stack.push(Double.NaN);
            throw new StackEmptyException("No elements in a stack");
        }
        stack.push(stack.peek());
    }
}
