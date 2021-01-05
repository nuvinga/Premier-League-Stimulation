package utilities.customErrors;

public class OptionSelectionError extends Exception{
    @Override
    public void printStackTrace(){
        System.out.println("\nOOPS THAT'S AN ERROR- Option selection unrecognized\n" +
                "The option you selected is not recognized. Please review the options available and enter the right choice.\n");
    }
}
