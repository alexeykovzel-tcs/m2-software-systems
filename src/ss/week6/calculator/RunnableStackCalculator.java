package ss.week6.calculator;

import ss.week5.calculator.StreamCalculator;

import java.io.Reader;
import java.io.Writer;

import static ss.week6.threads.console.SyncConsole.print;

public class RunnableStackCalculator implements Runnable {
    private final Reader reader;
    private final Writer writer;
    private final StreamCalculator calculator;

    public RunnableStackCalculator(Reader reader, Writer writer, StreamCalculator calculator) {
        this.reader = reader;
        this.writer = writer;
        this.calculator = calculator;
    }

    @Override
    public void run() {
        calculator.process(reader, writer);
    }
}

