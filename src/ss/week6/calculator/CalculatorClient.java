package ss.week6.calculator;

import ss.week5.calculator.CalculatorFactory;
import ss.week5.calculator.StackCalculatorFactory;

import java.io.*;

public class CalculatorClient {

    /**
     * Creates a stack calculator which operates in runtime. Whenever a value is written to the terminal,
     * it is processed by one of the static commands, otherwise processed by a calculator.
     * <p>
     * Stream readers and writers:
     * calcSnk  - writes and sends data to 'calcIn' reader
     * calcIn   - calculator reader for new data
     * calcOut  - printer for 'calcSnk'
     * sysOut   - terminal writer
     * sysIn    - terminal reader
     */
    public static void main(String[] args) {
        CalculatorFactory factory = new StackCalculatorFactory();

        try (PipedWriter calcSnk = new PipedWriter();
             PipedReader calcIn = new PipedReader(calcSnk);
             PrintWriter calcOut = new PrintWriter(calcSnk, true);
             OutputStreamWriter sysOut = new OutputStreamWriter(System.out);
             BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in))) {

            // launch calculator which returns results explicitly to the terminal
            new Thread(factory.makeRunnableStreamCalculator(calcIn, sysOut)).start();

            // listen to each new line in the terminal
            while (true) {
                String line = sysIn.readLine();

                // quit a program by 'quit' command, otherwise process it by calculator
                if (line.equals("quit")) {
                    System.exit(0);
                } else {
                    calcOut.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}