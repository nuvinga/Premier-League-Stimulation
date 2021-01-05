package utilities.customErrors;

public class InputFormatError extends Exception{

    private final String type;

    public InputFormatError(String type) {
        this.type = type;
    }

    @Override
    public void printStackTrace(){
        if (type.equals("name")) {
            System.out.println("\nOOPS THAT'S AN ERROR- Input type error\n" +
                    "You can NOT include symbols or integers (i.e. 1-9/@#$) in the name of the club. However, special characters " +
                    "such as (') and (.) are permitted\n");
        }else if (type.equals("location")){
            System.out.println("\nOOPS THAT'S AN ERROR- Input type error\n" +
                    "You can NOT include symbols in the location of the club. However, special characters such as (') " +
                    "and (.) are permitted\n");
        }else if (type.equals("date")){
            System.out.println("\nOOPS THAT'S AN ERROR- Input type error\n" +
                    "Date entries have the following restrictions:\n" +
                    "           Date- 1/2 digits from 1-31 depending on the month (months 2,4,6,9,11 can only have dates from 1-30)\n" +
                    "           Month- 1/2 digits from 1-12\n" +
                    "           Year- Only years from 1800-2099 are allowed\n");
        }else if (type.equals("coach")){
            System.out.println("\nOOPS THAT'S AN ERROR- Input type error\n" +
                    "You can NOT include symbols or integers (i.e. 1-9/@#$) in the name of the coach. However, special characters " +
                    "such as (') and (.) are permitted\n");
        }else if (type.equals("matchesInput")){
            System.out.println("\nOOPS THAT'S AN ERROR- Input type error\n" +
                    "You can NOT enter negative values as the number of matches. Please reconsider your input.\n");
        }
    }
}
