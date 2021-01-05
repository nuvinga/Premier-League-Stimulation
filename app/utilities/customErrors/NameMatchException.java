package utilities.customErrors;

public class NameMatchException extends Exception{
    @Override
    public void printStackTrace(){
        System.out.println("\nOOPS THAT'S AN ERROR- Club name already in use\nPlease consider to rename the club or check if your " +
                "manager has already registered the club\n");
    }
}
