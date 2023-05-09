package ss.week5.exception;

public class ArgumentLengthsDifferException extends WrongArgumentException {
    public ArgumentLengthsDifferException(int l1, int l2) {
        super(String.format("error: length of command line arguments differ (%d, %d)", l1, l2));
    }
}
