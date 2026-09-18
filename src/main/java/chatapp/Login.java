package chatapp;

import java.util.regex.Pattern;

/**
 * Login
 *
 * Handles user registration and login for Part 1 of the Chat App POE.
 *
 * Assumption (not fully visible on the supplied pages): the "Welcome
 * <first name>, <last name>" message requires the user's first and last
 * name, so this class also captures those two fields at registration.
 * If your brief captures first/last name somewhere else, just remove the
 * two fields/params here and wire it up accordingly.
 */
public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    // Regex: underscore required, whole username no more than 5 chars.
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*_).{1,5}$");

    // Regex: 8+ chars, at least one capital, one digit, one special character.
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$");

    // Regex: +27 country code followed by up to 10 digits.
    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+27\\d{1,10}$");

    /**
     * Checks that the username contains an underscore and is no more
     * than five characters long.
     */
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username).matches();
    }

    /**
     * Checks that the password is at least eight characters long and
     * contains a capital letter, a number, and a special character.
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Checks that the cell phone number contains the international
     * country code (+27) followed by a number no more than ten
     * characters long.
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) {
            return false;
        }
        return CELL_PATTERN.matcher(cellPhoneNumber).matches();
    }

    /**
     * Registers a user, validating username, password and cell number
     * in that order, and returns the appropriate feedback message.
     */
    public String registerUser(String firstName, String lastName, String username,
                                String password, String cellPhoneNumber) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital letter, "
                    + "a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an "
                    + "international code; please correct the number and try again.";
        }

        // All conditions met — store the user's details.
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        return "Username successfully captured. Password successfully captured. "
                + "Cell number successfully captured. Registration successful.";
    }

    /**
     * Verifies that the supplied username and password match the
     * details stored at registration.
     */
    public boolean loginUser(String username, String password) {
        if (this.username == null || this.password == null) {
            return false;
        }
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Returns the login feedback message based on whether the login
     * attempt succeeded.
     */
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + ", it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
