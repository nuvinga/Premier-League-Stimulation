package utilities;

import utilities.customErrors.InputFormatError;

public class DateUtility {

    public static String produceDate(String year, int month, int day) throws InputFormatError {

        if (year.matches("^(20)\\d{2}$")) { //year is validated using a regex
            //will proceed only if year starts with 20 and is followed by 2 more digits

            if ((((month == 2) | (month == 4) | (month == 6) | (month == 9) | (month == 11)) && ((day >= 1) && (day <= 30))) |
                    (((month == 1) | (month == 3) | (month == 5) | (month == 7) | (month == 8) | (month == 10) | (month == 12)) && ((day >= 1) && (day <= 31)))) {
                //since months 2,4,6,9 and 12 have only 30 days max and months 1,3,5,7,8,10 and 12 have 31 days
                return String.format("%02d/%02d/", day, month) + year; //formatting the date to have 0s

            } else throw (new InputFormatError("date")); //ref custom errors

        } else throw (new InputFormatError("date"));

    }

}
