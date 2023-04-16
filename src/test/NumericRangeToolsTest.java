package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import InputVerification.NumericRangeTool;

public class NumericRangeToolsTest {

    /**
     * @desc Validates if the input is within range of 0-100%
     * 
     * @subcontract value within valid range {
     *   @requires 0 <= percentage <= 100;
     *   @ensures \result = true;
     * }
     */

     @Test
     public void testInputIsValidRangeRequires0EnsuresTrue(){
         //Arrange
         int percentage = 0;
         //Act
         Boolean result = NumericRangeTool.validPercentage(percentage);
         //Assert
         assertEquals(true, result);
     }
 
     @Test
     public void testInputIsValidRangeRequires50EnsuresTrue(){
         //Arrange
         int percentage = 50;
         //Act
         Boolean result = NumericRangeTool.validPercentage(percentage);
         //Assert
         assertEquals(true, result);
     }
 
 
     @Test
     public void testInputIsValidRangeRequires100EnsuresTrue(){
         //Arrange
         int percentage = 100;
         //Act
         Boolean result = NumericRangeTool.validPercentage(percentage);
         //Assert
         assertEquals(true, result);
     }


     /* @subcontract value out of range low {
     *   @requires percentage < 0;
     *   @ensures \result = false;
     * }
     */

     @Test
     public void testInputIsValidRangeRequiresMin1EnsuresFalse(){
         //Arrange
         int percentage = -1;
         //Act
         Boolean result = NumericRangeTool.validPercentage(percentage);
         //Assert
         assertEquals(false, result);
     }


     /* @subcontract value out of range high {
     *   @requires percentage > 100;
     *   @signals \result = false;
     * }
     * 
     */

     @Test
     public void testInputIsValidRangeRequires101EnsuresFalse(){
         //Arrange
         int percentage = 101;
         //Act
         Boolean result = NumericRangeTool.validPercentage(percentage);
         //Assert
         assertEquals(false, result);
     }
     
}

