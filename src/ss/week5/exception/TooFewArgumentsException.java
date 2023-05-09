package ss.week5.exception;

public class TooFewArgumentsException extends WrongArgumentException {
    public TooFewArgumentsException(){
        super("error: too few command line arguments");
    }
}
