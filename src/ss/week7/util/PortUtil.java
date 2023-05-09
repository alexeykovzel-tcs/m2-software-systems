package ss.week7.util;

import java.io.BufferedReader;
import java.io.IOException;

public class PortUtil {
    /**
     * Convert and validate port from string to integer.
     * If a port is one of the reserved, it is considered as invalid.
     *
     * @param s Input string
     * @return null in case of an invalid port, otherwise the validated port
     */
    public static Integer format(String s) {
        int port;
        try {
            port = Integer.parseInt(s);
            if (port == 0 || (port >= 1024 && port <= 65536)) return port;
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Request port using a buffered reader.
     * @param in    buffered reader
     * @return      port
     */
    public static int request(BufferedReader in) throws IOException {
        Integer port;
        while (true) {
            System.out.print("Server port : ");
            port = format(in.readLine());
            if (port != null) break;
            System.out.println("Invalid port. Try again");
        }
        return port;
    }
}
