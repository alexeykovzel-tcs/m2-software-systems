package ss.week7.chat.client;

import ss.week7.chat.protocol.ChatProtocol;
import ss.week7.chat.server.RunnableChatServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final ChatProtocol protocol;
    private final Socket socket;
    private final RunnableChatServer chat;
    private final BufferedReader in;
    private final PrintWriter out;
    private String username;

    public ClientHandler(Socket socket, RunnableChatServer chat, ChatProtocol protocol) throws IOException {
        this.socket = socket;
        this.chat = chat;
        this.protocol = protocol;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendChat(String from, String message) {
        String encodedMessage = protocol.encodeFromMessage(from, message);
        if (encodedMessage != null) out.println(encodedMessage);
    }

    @Override
    public void run() {
        try {
            // ask for username
            String line = in.readLine();
            if (line == null) throw new IOException();
            setUsername(line);

            // handle chat messages
            while ((line = in.readLine()) != null) {
                chat.handleChatMessage(this, line);
            }
        } catch (IOException ignored) {
        } finally {
            close();
            chat.removeClient(this);
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Client is not responding...");
        }
    }

    public int getPort() {
        return socket.getPort();
    }

    public String getAddress() {
        return socket.getInetAddress().getHostAddress();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
