package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InputVerification.MailTools;

public class MailToolsTest {

    /*
     * @subcontract valid email {
     * 
     * @requires no other precondition
     * 
     * @ensures \result = true;
     * }
     */

    @Test
    public void testInputRequiresValidEmailEnsureTrue() {
        // Arrange
        String email = "test@example.com";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(true, result);

    }

    /*
     * @subcontract no mailbox part {
     * 
     * @requires !mailAddress.contains("@") ||
     * mailAddress.split("@")[0].length < 1;
     * 
     * @ensures \result = false;
     * }
     */

    @Test
    public void testInputRequiresNoMailGivenEnsureFalse() {
        // Arrange
        String email = "";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(false, result);
    }

    /*
     * @subcontract subdomain-tld delimiter {
     * 
     * @requires !mailAddress.contains("@") ||
     * mailAddress.split("@")[1].split(".").length > 2;
     * 
     * @ensures \result = false;
     * }
     */

    @Test
    public void testInputRequiresEmailWithoutAtSignEnsureFalse() {
        // Arrange
        String email = "testexample.com";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void testInputRequiresEmailWithoutCharactersBeforeAtSignEnsureFalse() {
        // Arrange
        String email = "@example.com";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(false, result);
    }

    @Test
    public void testInputRequiresEmailWithoutDomainEnsureFalse() {
        // Arrange
        String email = "test@.com";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(false, result);
    }

    /*
     * @subcontract no tld part {
     * 
     * @requires !mailAddress.contains("@") ||
     * mailAddress.split("@")[1].split(".")[1].length < 1;
     * 
     * @ensures \result = false;
     * }
     */

    @Test
    public void testInputRequiresEmailWithoutTLDEnsureFalse() {
        // Arrange
        String email = "test@example.";
        // Act
        Boolean result = MailTools.validateMailAddress(email);
        // Assert
        assertEquals(false, result);
    }

}
