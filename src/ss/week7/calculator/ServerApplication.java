package ss.week7.calculator;
import ss.week7.util.PortUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerApplication {
    public static void main(String[] args) {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.print("Server port: ");
                String input = br.readLine();
                Integer port = PortUtil.format(input);

                if (port != null) {
                    // deploy server with calculator
                    var server = new RunnableCalculatorServer();
                    server.start(port);

                    // listen to terminal input
                    while ((input = br.readLine()) != null) {
                        if (input.equals("quit")) {
                            server.stop();
                            break;
                        }
                    }
                    break;
                }
                System.out.println("Invalid port. Try again");
            }
        } catch (IOException e) {
            System.out.println("Oops.. Server has been interrupted");
        }
    }
}
