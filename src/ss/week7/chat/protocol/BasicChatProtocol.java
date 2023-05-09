package ss.week7.chat.protocol;

public class BasicChatProtocol implements ChatProtocol {
    @Override
    public String encodeSayMessage(String message) {
        if (!verifyMessage(message)) return null;
        return "SAY~" + message;
    }

    @Override
    public String encodeFromMessage(String username, String message) {
        if (!verifyUsername(username) || !verifyMessage(message)) return null;
        return String.format("FROM~%s~%s", username, message);
    }

    @Override
    public String decodeSayMessage(String encodedMessage) {
        String[] args = encodedMessage.split("~");
        if (args.length != 2 || !args[0].equals("SAY")) return null;
        return args[1];
    }

    @Override
    public String[] decodeFromMessage(String encodedMessage) {
        String[] args = encodedMessage.split("~");
        if (args.length != 3 || !args[0].equals("FROM")) return null;
        return new String[]{args[1], args[2]};
    }

    @Override
    public boolean verifyUsername(String username) {
        return !username.contains("~");
    }

    @Override
    public boolean verifyMessage(String message) {
        return !message.contains("~");
    }
}