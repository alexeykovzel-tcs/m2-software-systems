package ss.week4.calculator;

import java.io.Reader;
import java.io.Writer;

public class StackCalculatorFactory implements CalculatorFactory{
    @Override
    public Calculator makeCalculator() {
        return new StackCalculator();
    }

    @Override
    public StreamCalculator makeStreamCalculator(Calculator calculator) {
        return null;
    }

    @Override
    public Runnable makeRunnableStreamCalculator(Reader reader, Writer writer) {
        return null;
    }

    @Override
    public CalculatorServer makeCalculatorServer() {
        return null;
    }
}
