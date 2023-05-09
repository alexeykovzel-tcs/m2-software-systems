package ss.week7.chat.protocol;

public interface ChatProtocol {
    String encodeSayMessage(String message);

    String encodeFromMessage(String from, String message);

    String decodeSayMessage(String message);

    String[] decodeFromMessage(String message);

    boolean verifyUsername(String username);

    boolean verifyMessage(String message);
}
