package ss.week7.chat.server;

import ss.week7.chat.client.ClientHandler;
import ss.week7.chat.protocol.ChatProtocol;
import ss.week7.chat.protocol.BasicChatProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RunnableChatServer implements ChatServer, Runnable {
    private final List<ClientHandler> clients = new ArrayList<>();
    private ChatProtocol protocol;
    private ServerSocket socket;
    private Thread serverThread;
    private int port;

    public RunnableChatServer(int port, int protocolCode) {
        this.port = port;
        if (protocolCode == 0) this.protocol = new BasicChatProtocol();
    }

    public RunnableChatServer(int protocolCode) {
        this(8080, protocolCode);
    }

    public synchronized void handleChatMessage(ClientHandler from, String sayMessage) {
        String message = protocol.decodeSayMessage(sayMessage);
        if (message != null) {
            clients.forEach((client) -> client.sendChat(from.getUsername(), message));
        }
    }

    @Override
    public void start() throws IOException {
        socket = new ServerSocket(port);
        port = socket.getLocalPort();
        System.out.printf("Deploying server '%s' on port %d...%n",
                socket.getInetAddress().getHostAddress(), getPort());
        serverThread = new Thread(this);
        serverThread.start();
    }

    @Override
    public int getPort() {
        return socket.getLocalPort();
    }

    @Override
    public void stop() {
        try {
            clients.forEach(ClientHandler::close);
            socket.close();
            serverThread.join();
        } catch (InterruptedException | IOException e) {
            System.out.println("Server is not responding...");
        } finally {
            System.out.println("Closing server...");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                var clientSocket = socket.accept();
                var client = new ClientHandler(clientSocket, this, protocol);
                addClient(client);
                new Thread(client).start();
            } catch (IOException e) {
                break;
            }
        }
    }

    public synchronized void addClient(ClientHandler client) {
        System.out.printf("Connecting client '%s' on port %d...%n", client.getAddress(), client.getPort());
        clients.add(client);
    }

    public synchronized void removeClient(ClientHandler client) {
        System.out.printf("Disconnecting client '%s'...%n", client.getAddress());
        clients.remove(client);
    }
}
