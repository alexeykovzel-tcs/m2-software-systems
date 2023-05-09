package ss.week5.calculator;

import java.io.*;

public class CalculatorClient {
    public static void main(String[] args) {
        CalculatorFactory factory = new StackCalculatorFactory();
        StreamCalculator calculator = factory.makeStreamCalculator(factory.makeCalculator());

        Reader input = new InputStreamReader(System.in);
        Writer output = new OutputStreamWriter(System.out);

        calculator.process(input, output);
    }
}
