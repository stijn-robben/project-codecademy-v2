package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InputVerification.PostalCode;

public class PostalCodeTest {

    // empty postcode
    @Test(expected = NullPointerException.class)
    public void testInputRequiresNullExpectNullPointerException() {
        PostalCode.formatPostalCode(null);
    }

    // Spaces added and letters capitalized.
    @Test
    public void testInputRequires1111aAEnsures1111_AA() {
        // Arrange Act
        String result = PostalCode.formatPostalCode("1111aA");
        // Assert
        assertEquals("1111 AA", result);
    }

    // Spaces removed
    @Test
    public void testInputRequires_1111_AA_Ensures1111_AA() {
        // Arrange Act
        String result = PostalCode.formatPostalCode(" 1111 AA ");
        // Assert
        assertEquals("1111 AA", result);
    }

    // Correct postalcodes
    @Test
    public void testInputRequires1234_ABEnsures1234_AB() {
        // // Arrange Act
        String result = PostalCode.formatPostalCode("1234 AB");
        // Assert
        assertEquals("1234 AB", result);
    }

    // charachter used that is not in alphabet
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequires1234AInvalidSignGivesIllegalArgumentException() {
        // // Arrange Act
        PostalCode.formatPostalCode("1234A$");
    }

    // less than 4 numbers
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequiresRequires111AB_GivesIllegalArgumentException() {
        // Arrange Act
        PostalCode.formatPostalCode("111AB");
    }

    // More than 4 numbers
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequires11111AB_GivesIllegalArgumentException() {
        // Execute
        PostalCode.formatPostalCode("11111AB");
    }

    // more than 2 symbols
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequires1000ABCGivesIllegalArgumentException() {
        // Execute
        PostalCode.formatPostalCode("1000AAA");
    }

    // less than 2 letters
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequiresRequires1000A_GivesIllegalArgumentException() {
        // Arrange Act
        PostalCode.formatPostalCode("1000A");
    }

    // Letter part contains numbers
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequires1234_5AGivesIllegalArgumentException() {
        // Execute
        PostalCode.formatPostalCode("1234 5A");
    }

    // Number part contains letters
    @Test(expected = IllegalArgumentException.class)
    public void testInputRequiresAAAA_AAGivesIllegalArgumentException() {
        // Arrange Act
        PostalCode.formatPostalCode("123A AA");
    }

}
