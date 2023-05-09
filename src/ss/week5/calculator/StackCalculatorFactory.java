package ss.week5.calculator;

import ss.week6.calculator.RunnableStackCalculator;
import ss.week7.calculator.RunnableCalculatorServer;

import java.io.Reader;
import java.io.Writer;

public class StackCalculatorFactory implements CalculatorFactory {
    @Override
    public Calculator makeCalculator() {
        return new StackCalculator();
    }

    @Override
    public StreamCalculator makeStreamCalculator(Calculator calculator) {
        return new StackStreamCalculator(calculator);
    }

    @Override
    public Runnable makeRunnableStreamCalculator(Reader reader, Writer writer) {
        return new RunnableStackCalculator(reader, writer, makeStreamCalculator(makeCalculator()));
    }

    @Override
    public CalculatorServer makeCalculatorServer() {
        return new RunnableCalculatorServer();
    }
}