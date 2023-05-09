package ss.week7.chat.client;

import ss.week7.chat.protocol.ChatProtocol;
import ss.week7.chat.protocol.BasicChatProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements ChatClient, Runnable {
    private final List<ChatListener> listeners = new ArrayList<>();
    private final ChatProtocol protocol;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private String username;

    public Client(ChatProtocol protocol) {
        this.protocol = protocol;
    }

    public Client() {
        this(new BasicChatProtocol());
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                for (ChatListener listener : listeners) {
                    listener.messageReceived(username, line);
                }
            }
        } catch (IOException ignored) {
        } finally {
            if (!socket.isClosed()) close();
        }
    }

    @Override
    public boolean connect(InetAddress address, int port) {
        try {
            socket = new Socket(address, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connecting to server...");
            new Thread(this).start();
            return true;
        } catch (IOException e) {
            System.out.println("Could not connect to server...");
            return false;
        }
    }

    @Override
    public boolean sendUsername(String username) {
        if (socket.isClosed() || !protocol.verifyUsername(username)) return false;
        this.username = username;
        out.println(username);
        return true;
    }

    @Override
    public boolean sendMessage(String sayMessage) {
        if (socket.isClosed()) return false;
        String message = protocol.decodeSayMessage(sayMessage);
        if (message == null) return false;
        out.println(sayMessage);
        return true;
    }

    @Override
    public void addChatListener(ChatListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeChatListener(ChatListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void close() {
        try {
            socket.close();
            out.printf("Disconnecting client '%s'...%n", socket.getInetAddress().getHostAddress());
            System.out.println("Disconnecting from server...");
        } catch (IOException e) {
            System.out.println("Client is not responding...");
        }
    }
}
