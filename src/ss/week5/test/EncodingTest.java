package ss.week5.test;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * A simple class that experiments with the Hex encoding
 * of the Apache Commons Codec library.
 */
public class EncodingTest {
    public static void main(String[] args) throws DecoderException {
        String input1 = "Hello Big World";
        String hex = Hex.encodeHexString(input1.getBytes());
        System.out.println(new String(hex.getBytes()));

        String input2 = "010203040506";
        System.out.println(new String(Base64.encodeBase64(input2.getBytes())));

        String input3 = "U29mdHdhcmUgU3lzdGVtcw==";
        System.out.println(new String(Base64.decodeBase64(input3.getBytes())));

        for (int i = 1; i <= 10; i++) {
            String val = "a".repeat(i);
            System.out.println(new String(Base64.encodeBase64(val.getBytes())));
        }
    }
}
