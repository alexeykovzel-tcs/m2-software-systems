package ss.week7.chat.client;

import ss.week7.chat.protocol.ChatProtocol;
import ss.week7.chat.protocol.BasicChatProtocol;
import ss.week7.util.PortUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Chat implements ChatListener {
    private final ChatProtocol protocol;

    public Chat(ChatProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public void messageReceived(String from, String message) {
        String encodeMessage = protocol.encodeFromMessage(from, message);
        if (encodeMessage != null) System.out.println(encodeMessage);
    }

    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));

        InetAddress address;
        while (true) {
            System.out.print("Server address : ");
            try {
                address = InetAddress.getByName(in.readLine());
                break;
            } catch (UnknownHostException e) {
                System.out.println("Invalid host address. Try again");
            }
        }

        int port = PortUtil.request(in);
        System.out.print("What's your username? : ");
        String username = in.readLine();

        var protocol = new BasicChatProtocol();
        var client = new Client(protocol);
        client.addChatListener(new Chat(protocol));
        boolean connected = client.connect(address, port);
        if (!connected || !client.sendUsername(username)) {
            System.exit(0);
        }

        String message;
        while ((message = in.readLine()) != null) {
            if (message.equals("quit")) {
                client.close();
                break;
            }
            boolean delivered = client.sendMessage(message);
            if (!delivered) System.out.printf("Could not send '%s'...%n", message);
        }
    }
}
