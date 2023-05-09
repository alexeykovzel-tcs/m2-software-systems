package ss.week7.chat.client;

public interface ChatListener {
    void messageReceived(String from, String message);
}
