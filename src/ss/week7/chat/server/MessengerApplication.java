package ss.week7.chat.server;

import ss.week7.util.PortUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessengerApplication {
    public static void main(String[] args) throws IOException {
        var in = new BufferedReader(new InputStreamReader(System.in));
        int port = PortUtil.request(in);
        var server = new RunnableChatServer(port, 0);
        server.start();

        String line;
        while ((line = in.readLine()) != null) {
            if (line.equals("quit")) server.stop();
        }
    }
}
