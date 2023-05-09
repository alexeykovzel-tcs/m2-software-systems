package ss.week3.password.checker;

public class StrongChecker extends BasicChecker {
    @Override
    public boolean isAcceptable(String suggestion) {
        char firstChar = suggestion.charAt(0);
        char lastChar = suggestion.charAt(suggestion.length() - 1);

        return super.isAcceptable(suggestion)
                && Character.isLetter(firstChar)
                && Character.isDigit(lastChar);
    }

    @Override
    public String generatePassword() {
        return "Password123";
    }
}
