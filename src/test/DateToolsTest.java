package test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import InputVerification.DateTools;

public class DateToolsTest {

    /**
     * @desc Validates is a given date in the form of day, month and year is valid.
     * 
     * @subcontract 31 days in month {
     * @requires (month == 1 || month == 3 || month == 5 || month == 7 ||
     *           month == 8 || month == 10 || month == 12) && 1 <= day <= 31;
     * @ensures \result = true;
     *          }
     */

    @Test
    public void testInputRequires31DaysInMonth3Year2023EnsuresTrue() {
        // Arange
        int day = 31, month = 3, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(true, result);
    }

    @Test
    public void testInputRequires1DayInMonth1Year2023EnsuresTrue() {
        // Arange
        int day = 1, month = 1, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(true, result);
    }

    /*
     * @subcontract 30 days in month {
     * 
     * @requires (month == 4 || month == 6 || month == 9 || month == 11) &&
     * 1 <= day <= 30;
     * 
     * @ensures \result = true;
     * }
     */

    @Test
    public void testInputRequires30DaysInMonth4Year2023EnsuresTrue() {
        // Arange
        int day = 30, month = 4, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(true, result);
    }

    @Test
    public void testInputRequires1DayInMonth42023YearsEnsuresTrue() {

        // Arrange
        int days = 1, month = 4, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(days, month, year);
        // Assert
        Assert.assertEquals(true, result);

    }

    /*
     * @subcontract 29 days in month {
     * 
     * @requires month == 2 && 1 <= day <= 29 &&
     * (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
     * 
     * @ensures \result = true;
     * }
     */

    @Test
    public void testInputRequires29DaysMonth2Year2020EnsuresTrue() {

        // Arrange
        int days = 29, month = 2, year = 2020;
        // Act
        Boolean result = DateTools.validateDate(days, month, year);
        // Assert
        Assert.assertEquals(true, result);

    }

    @Test
    public void testInputRequires29DaysMonth2Year2016EnsuresTrue() {

        // Arrange
        int days = 29, month = 2, year = 2016;
        // Act
        Boolean result = DateTools.validateDate(days, month, year);
        // Assert
        Assert.assertEquals(true, result);

    }

    /*
     * @subcontract 28 days in month {
     * 
     * @requires month == 2 && 1 <= day <= 28 &&
     * (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0));
     * 
     * @ensures \result = true;
     * }
     */

    @Test
    public void testInputRequires28DaysMonth2Year2023EnsuresTrue() {

        // Arrange
        int days = 28, month = 2, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(days, month, year);
        // Assert
        Assert.assertEquals(true, result);

    }

    @Test
    public void testInputRequires28DaysMonth2Year1910EnsuresTrue() {

        // Arrange
        int days = 28, month = 2, year = 1910;
        // Act
        Boolean result = DateTools.validateDate(days, month, year);
        // Assert
        Assert.assertEquals(true, result);

    }

    /*
     * @subcontract all other cases {
     * 
     * @requires no other accepting precondition;
     * 
     * @ensures \result = false;
     * }
     * 
     */

    // check if month can't have more than 32 days
    @Test
    public void testInputRequiresDays32Month1Year2023EnsuresFalse() {
        // Arrange
        int day = 32, month = 1, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);
    }

    // check if month of 30 days can't have more
    @Test
    public void testInputRequiresDays31Month1Year2023YearEnsuresFalse() {
        // Arrange
        int day = 31, month = 4, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);

    }

    // check if february in a no leap year doesn't have more than 28 days
    @Test
    public void testInputRequiresDays29Month2Year2023YearEnsuresFalse() {
        // Arrange
        int day = 29, month = 2, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);
    }

    // test if day can't be less than 1
    @Test
    public void testInputRequiresDays0Month1Year2023EnsuresFalse() {
        // Arrange
        int day = 0, month = 1, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);
    }

    // test if month can't be less than 1
    @Test
    public void testInputRequiresDays1Month0Year2023EnsuresFalse() {
        // Arrange
        int day = 1, month = 0, year = 2023;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);
    }

    // Test if year isn't before 1910
    @Test
    public void testInputRequiresDays1Month1Year1909EnsuresFalse() {
        // Arrange
        int day = 1, month = 1, year = 1909;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);
    }

    // Test a date in the future
    @Test
    public void testInputRequiresMonth5Day15NextYearEnsuresFalse() {
        // Arrange
        int day = 15, month = 5, year = LocalDate.now().getYear() + 1;
        // Act
        Boolean result = DateTools.validateDate(day, month, year);
        // Assert
        Assert.assertEquals(false, result);
    }

}
