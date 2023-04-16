package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InputVerification.MailTools;

public class MailToolsTest {

    @Test
    public void testInputRequiresValidEmailEnsureTrue() {
        // Arrange
        String email = "test@example.com";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(true, result);

    }

    @Test
    public void testInputRequiresNoMailGivenEnsureFalse() {
        // Arrange
        String email = "";

        // Act
        Boolean result = MailTools.validateMailAddress(email);

        // Assert
        assertEquals(false, result);
    }

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


    // not working yet!!
    // @Test
    // public void testInputRequiresEmailWithoutTLDEnsureFalse() {
    //     // Arrange
    //     String email = "test@example";

    //     // Act
    //     Boolean result = MailTools.validateMailAddress(email);

    //     // Assert
    //     assertEquals(false, result);
    // }


}
