package ss.week3.password;

public class BasicPassword {
    /**
     * The standard initial password.
     */
    public static final String INITIAL = "Password";

    /**
     * Password value.
     */
    private String value = INITIAL;

    /**
     * Constructs a Password with the initial word provided in INITIAL.
     */
    public BasicPassword() {
    }

    /**
     * Changes this password.
     *
     * @param oldPassword - The current password
     * @param newPassword - The new password
     * @return true If oldPassword is equal to the current password and newPassword is an acceptable password
     */
    public boolean setWord(String oldPassword, String newPassword) {
        if (testWord(oldPassword) && isAcceptable(newPassword)) {
            value = newPassword;
            return true;
        }
        return false;
    }

    /**
     * Tests if a given word is equal to the current password.
     *
     * @param word - Word that should be tested
     * @return true If test is equal to the current password
     */
    public boolean testWord(String word) {
        return word.equals(value);
    }

    /**
     * Test if a given string is an acceptable password.
     *
     * @param suggestion - Word that should be tested
     * @return true If suggestion is acceptable
     */
    public boolean isAcceptable(String suggestion) {
        return suggestion.length() > 6 && !suggestion.contains(" ");
    }
}
