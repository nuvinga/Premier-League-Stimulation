package utilities.customErrors;

public class DateError extends Exception{

    @Override
    public void printStackTrace(){
        System.out.println("\nOOPS THAT'S AN ERROR- Date of Formation mismatch\n" +
                "The date you have entered for the match refers to a date before the registration of the club. Please " +
                "reconsider your input.\n");
    }

}
