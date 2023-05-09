package ss.week7.calculator;

import ss.week7.util.PortUtil;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientApplication {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Server address: ");
            String hostAddress = InetAddress.getByName(br.readLine()).getHostAddress();

            while (true) {
                System.out.print("Server port: ");
                Integer port = PortUtil.format(br.readLine());

                if (port != null) {
                    // connect to calculator server
                    Socket socket = new Socket(hostAddress, port);
                    System.out.printf("Connecting to '%s'...%n", socket.getLocalAddress().getHostAddress());

                    // print server responses
                    new Thread(new ResponseListener(socket)).start();

                    // listen and send user input to server
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String input;
                    while ((input = br.readLine()) != null) {
                        if (input.equals("quit")) {
                            socket.close();
                        } else {
                            out.println(input);
                        }
                    }
                    break;
                }
                System.out.println("Invalid port. Try again");
            }
        } catch (IOException e) {
            System.out.println("Could not connect to server...");
        }
    }

    static class ResponseListener implements Runnable {
        private final Socket socket;

        public ResponseListener(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                var sysOut = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response;
                while ((response = sysOut.readLine()) != null) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                System.out.println("Oops... Server is not responding");
            }
        }
    }
}
