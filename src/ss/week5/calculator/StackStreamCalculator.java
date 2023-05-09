package ss.week5.calculator;

import ss.week5.calculator.exception.DivideByZeroException;
import ss.week5.calculator.exception.StackEmptyException;

import java.io.*;
import java.util.Optional;

public class StackStreamCalculator implements StreamCalculator {
    final Calculator calculator;

    StackStreamCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void process(Reader input, Writer output) {
        try (BufferedReader in = new BufferedReader(input);
             PrintWriter out = new PrintWriter(output, true)) {

            String line;
            while ((line = in.readLine()) != null) {
                String[] args = line.split(" ");
                Optional<CalculatorAction> action = CalculatorAction.byCall(args[0]);
                if (action.isEmpty()) {
                    out.println("error: invalid operation");
                } else {
                    try {
                        switch (action.get()) {
                            case POP:
                                double val = calculator.pop();
                                out.println(val);
                                break;
                            case PUSH:
                                double arg = Double.parseDouble(args[1]);
                                calculator.push(arg);
                                break;
                            default:
                                action.get().execute(calculator);
                        }
                    } catch (StackEmptyException e) {
                        out.println("error: no elements in a stack");
                    } catch (DivideByZeroException e) {
                        out.println("error: division by 0 is not allowed");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("error: illegal number of arguments");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}