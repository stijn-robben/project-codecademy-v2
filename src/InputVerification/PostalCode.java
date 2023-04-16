package InputVerification;

public class PostalCode {

    

    public static String formatPostalCode(String postalCode) throws IllegalArgumentException, NullPointerException {
        //Postalcode can't  be empty.
        if(postalCode == null){
            throw new NullPointerException("Postcode niet ingevoerd");
        }

        // Remove empty characters from the postalcode.
        String validPostalCode = postalCode.replaceAll(" ", "");
        try {
            int postalcodeNumbers = Integer.valueOf(validPostalCode.substring(0, 4));

            //format letters to uppercase
            String postalcodeLetters = validPostalCode.substring(4).toUpperCase();
            char firstLetter = postalcodeLetters.charAt(0);
            char secondLetter = postalcodeLetters.charAt(1);

            //Check to see if the number part of postcode contains 4 numbers, and the letter part 2 letters. + if letters are between a and z.
            if (postalcodeNumbers > 999 && postalcodeNumbers <= 9999 && postalcodeLetters.length() == 2
                    && ('A' <= firstLetter && firstLetter <= 'Z')
                    && ('A' <= secondLetter && secondLetter <= 'Z')) {

                // Return postalcode with a whitespace between the numbers and uppercase letters.
                return String.format("%d %s", postalcodeNumbers, validPostalCode.trim().substring(4).toUpperCase());
            } else {
                throw new IllegalArgumentException("De postcode is incorrect");
            }
        }catch(Exception e){
            // In case of any error
            throw new IllegalArgumentException("De postcode is incorrect");
        }
    }
}
