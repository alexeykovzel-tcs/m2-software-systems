package ss.week3.password;

import ss.week3.password.checker.BasicChecker;
import ss.week3.password.checker.Checker;

public class Password {
    public static final String DEFAULT = "default";

    private final Checker checker;
    private String passValue;

    public Password() {
        this(new BasicChecker());
    }

    public Password(Checker checker) {
        this.checker = checker;
        passValue = DEFAULT;
    }

    public boolean setValue(String oldPassValue, String newPassValue) {
        if (testPassValue(oldPassValue) && isAcceptable(newPassValue)) {
            passValue = newPassValue;
            return true;
        }
        return false;
    }
    
    public String getValue(){
        return passValue;
    }

    public Checker getChecker() {
        return checker;
    }

    public boolean testPassValue(String word) {
        return word.equals(passValue);
    }

    public boolean isAcceptable(String suggestion) {
        return suggestion.length() > 6 && !suggestion.contains(" ");
    }
}
