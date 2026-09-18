package chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * LoginTest
 *
 * Uses the exact test data supplied in the POE brief so results line
 * up with what the marker expects.
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    // ---- checkUserName ----

    @Test
    public void testCheckUserName_correctlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_incorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    // ---- checkPasswordComplexity ----

    @Test
    public void testCheckPasswordComplexity_meetsRequirements() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_doesNotMeetRequirements() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---- checkCellPhoneNumber ----

    @Test
    public void testCheckCellPhoneNumber_correctlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_incorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---- registerUser ----

    @Test
    public void testRegisterUser_usernameIncorrect() {
        String result = login.registerUser("Kyle", "Smith", "kyle!!!!!!",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your "
                + "username contains an underscore and is no more than five "
                + "characters in length.", result);
    }

    @Test
    public void testRegisterUser_passwordIncorrect() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the "
                + "password contains at least eight characters, a capital letter, "
                + "a number, and a special character.", result);
    }

    @Test
    public void testRegisterUser_successful() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username successfully captured. Password successfully captured. "
                + "Cell number successfully captured. Registration successful.", result);
    }

    // ---- loginUser / returnLoginStatus (assertTrue/False) ----

    @Test
    public void testLoginUser_successful() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginUser_failed() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPassword1!"));
    }

    @Test
    public void testReturnLoginStatus_successful() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith, it is great to see you again.",
                login.returnLoginStatus(loggedIn));
    }

    @Test
    public void testReturnLoginStatus_failed() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loggedIn = login.loginUser("kyl_1", "wrongPassword1!");
        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus(loggedIn));
    }
}
