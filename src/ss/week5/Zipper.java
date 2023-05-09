package ss.week5;

import ss.week5.exception.ArgumentLengthsDifferException;
import ss.week5.exception.TooFewArgumentsException;

public class Zipper {
    /**
     * @return the zipped result String
     */
    /*@ requires s1 != null & s2 != null;
    requires s1.length() == s2.length();
     @*/
    public static String zip(String s1, String s2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            result.append(s1.charAt(i)).append(s2.charAt(i));
        }
        return result.toString();
    }

    /**
     * zips 2 strings
     * @param s1
     * @param s2
     * @return
     * @throws TooFewArgumentsException
     * @throws ArgumentLengthsDifferException
     */
    public static String zip2(String s1, String s2) throws TooFewArgumentsException, ArgumentLengthsDifferException {
        if (s1 == null || s2 == null) throw new TooFewArgumentsException();
        if (s1.length() != s2.length()) throw new ArgumentLengthsDifferException(s1.length(), s2.length());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            result.append(s1.charAt(i)).append(s2.charAt(i));
        }
        return result.toString();
    }


    public static void main(String[] args) throws TooFewArgumentsException, ArgumentLengthsDifferException {
        String s1 = args.length >= 1 ? args[0] : null;
        String s2 = args.length >= 2 ? args[1] : null;
        System.out.println(zip2(s1, s2));
    }
}
