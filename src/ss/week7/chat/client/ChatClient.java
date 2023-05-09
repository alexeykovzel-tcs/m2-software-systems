package ss.week7.chat.client;

import java.net.InetAddress;

public interface ChatClient {
    boolean connect(InetAddress address, int port);

    boolean sendUsername(String username);

    boolean sendMessage(String message);

    void addChatListener(ChatListener listener);

    void removeChatListener(ChatListener listener);

    void close();
}
