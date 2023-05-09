package ss.week7.calculator;

import ss.week5.calculator.CalculatorServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RunnableCalculatorServer implements CalculatorServer, Runnable {
    private final List<CalculatorClientHandler> handlers = new ArrayList<>();
    private ServerSocket socket;
    private Thread serverThread;

    @Override
    public void start(int port) throws IOException {
        socket = new ServerSocket(port);
        System.out.printf("Deploying server '%s' on port %d...%n",
                socket.getInetAddress().getHostAddress(), socket.getLocalPort());
        serverThread = new Thread(this);
        serverThread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = socket.accept();
                var clientHandler = new CalculatorClientHandler(clientSocket);
                handlers.add(clientHandler);
                System.out.printf("Connecting '%s' on port %d...%n",
                        clientSocket.getLocalAddress().getHostAddress(), clientSocket.getPort());
                new Thread(clientHandler).start();
            } catch (IOException e) {
                break;
            }
        }
    }

    @Override
    public int getPort() {
        return socket.getLocalPort();
    }

    @Override
    public void stop() {
        try {
            for (CalculatorClientHandler handler : handlers) {
                handler.close();
            }
            socket.close();
        } catch (IOException e) {
            System.out.println("Oops... Server is not responding");
        } finally {
            try {
                serverThread.join();
            } catch (InterruptedException e) {
                System.out.println("Server has been interrupted...");
            }
            System.out.println("Closing server...");
        }
    }
}
