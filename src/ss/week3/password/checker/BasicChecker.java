package ss.week3.password.checker;

public class BasicChecker implements Checker{
    @Override
    public boolean isAcceptable(String suggestion) {
        return suggestion.length() > 6 && !suggestion.contains(" ");
    }

    @Override
    public String generatePassword() {
        return "Password";
    }
}
