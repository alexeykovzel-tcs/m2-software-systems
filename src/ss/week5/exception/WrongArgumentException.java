package ss.week5.exception;

public class WrongArgumentException extends Exception {
    public WrongArgumentException(String s) {
        super(s);
    }

    public WrongArgumentException() {
        super("Wrong arguments");
    }
}
