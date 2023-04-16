package InputVerification;

import java.time.LocalDate;

public class DateTools {

    public static boolean validateDate(int day, int month, int year) {

        // check if year is not below 1900 and not above current year
        if (year < 1910 || year > LocalDate.now().getYear() || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        // 31 days in month check
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                && (day >= 1 && day <= 31)) {
            return true;
        }

        // 30 days in month check
        if ((month == 4 || month == 6 || month == 9 || month == 11) && (day <= 30 && day >= 1)) {
            return true;
        }

        // check 29 days in month
        if ((month == 2 && (day >= 1 && day <= 29) && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)))) {
            return true;
        }

        if ((month == 2 && (day >= 1 && day <= 28)) && (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))) {
            return true;
        }

        return false;


    }
}
