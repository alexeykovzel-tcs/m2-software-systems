package ss.week7.calculator;

import ss.week5.calculator.StackCalculatorFactory;
import ss.week5.calculator.StreamCalculator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CalculatorClientHandler implements Runnable {
    private final StreamCalculator calculator;
    private final InputStreamReader in;
    private final OutputStreamWriter out;
    private final Socket socket;

    public CalculatorClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        var factory = new StackCalculatorFactory();
        this.calculator = factory.makeStreamCalculator(factory.makeCalculator());
        in = new InputStreamReader(socket.getInputStream());
        out = new OutputStreamWriter(socket.getOutputStream());
    }

    public void close() throws IOException {
        socket.close();
    }

    @Override
    public void run() {
        try {
            calculator.process(in, out);
        } finally {
            System.out.printf("Disconnecting client '%s'...%n", socket.getLocalAddress().getHostName());
        }
    }
}
