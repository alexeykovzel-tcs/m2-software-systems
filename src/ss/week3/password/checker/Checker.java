package ss.week3.password.checker;

public interface Checker {
    /**
     * Test if a given string is an acceptable password.
     *
     * @param suggestion    word that should be tested
     * @return              true If suggestion is acceptable
     */
    boolean isAcceptable(String suggestion);

    /**
     * Shows a password example.
     * @return  an example of an acceptable password
     */
    String generatePassword();
}
