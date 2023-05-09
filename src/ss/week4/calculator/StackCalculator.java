package ss.week4.calculator;

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
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            throw new StackEmptyException("Not enough elements in a stack");
        }
    }

    @Override
    public void add() throws StackEmptyException {
        stack.push(stack.pop() + stack.pop());
    }

    @Override
    public void sub() throws StackEmptyException {
        stack.push(-stack.pop() + stack.pop());
    }

    @Override
    public void mult() throws StackEmptyException {
        stack.push(stack.pop() * stack.pop());
    }

    @Override
    public void div() throws DivideByZeroException, StackEmptyException {
        double divisor = stack.pop();
        double divider = stack.pop();

        if (divisor != 0) {
            stack.push(divider / divisor);
        } else {
            stack.push(Double.NaN);
            throw new DivideByZeroException("Division by 0 is not allowed");
        }
    }

    @Override
    public void mod() throws DivideByZeroException, StackEmptyException {
        double divisor = stack.pop();
        double divider = stack.pop();

        if (divisor != 0) {
            stack.push(divider % divisor);
        } else {
            stack.push(Double.NaN);
            throw new DivideByZeroException("Division by 0 is not allowed");
        }
    }

    @Override
    public void dub() throws StackEmptyException{
        if (stack.size() != 0){
            stack.push(stack.peek());
        } else {
            stack.push(Double.NaN);
            throw new StackEmptyException("No elements in a stack");
        }
    }
}
