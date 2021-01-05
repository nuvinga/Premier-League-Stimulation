package applications;

import entities.SportsClub;
import utilities.customErrors.DateError;
import utilities.customErrors.InputFormatError;
import utilities.customErrors.NameMatchException;
import utilities.customErrors.OptionSelectionError;
import entities.Football.FootballClub;
import services.LeagueManager;
import services.PremierLeagueManager;
import utilities.DateUtility;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    /* Instance of the premier league manager class */
    private static final LeagueManager premierLeagueService = PremierLeagueManager.getInstance();
    /* Scanner object to use throughout class */
    private static final Scanner input = new Scanner(System.in);
    /* Tracks if user makes any changes to the data inorder to prompt appropriate messages where saving and loading
                    data from text file is needed */
    private static int dataManipulationTrack = 0;

    public static void main(String[] args) {
        System.out.println("\n\t\t~~~~! The Premier League Manager Command Line Interface Season 2020/21 !~~~~");
        try {
            premierLeagueService.loadSeasonStatus(); //loading the data from the previous run
        } catch ( Exception e ){ //if file not found, corrupted or un-readable- prompts message
            System.out.println("\nPREVIOUS DATA NOT LOADED- Log data file not found \nIf you are running this program for the first time, please ignore this message.\n" +
                    "If not, the log file has disappeared from the system or may have been corrupted. Please make sure you did not move any files from the folder dedicated for this program.");
        }
        home();
    }

    public static void home(){
        /* creating repeated variables outside all loops to minimize garbage collection */
        int option = 0; // to get the input and validate through case-statements
        do {
            System.out.print("\nHOME ~~~ \n\t[1] - Add new club to Premier League\n\t[2] - Remove club from Premier League" +
                    "\n\t[3] - View club statistics\n\t[4] - View Premier League Table \n\t[5] - Add new match results" +
                    "\n\t[6] - Save all changes to file as new version\n\t[7] - Load last saved version\n\t[8] - Open GUI Application" +
                    "\n\t[9] - Exit Application \n\nPlease enter the code ([x]) of the option you wish to execute: ");
            try {
                option = Integer.parseInt(input.nextLine()); //gets an integer input inside try and catch to catch string or other errors
                switch (option){
                    case 1: {
                        addClub();
                        break;
                    }
                    case 2: {
                        removeClub();
                        break;
                    }
                    case 3: {
                        viewClub();
                        break;
                    }
                    case 4: {
                        printTable();
                        break;
                    }
                    case 5: {
                        playMatch();
                        break;
                    }
                    case 6: {
                        dataManipulationTrack = 0;
                        saveSeasonStatus();
                        break;
                    }
                    case 7: {
                        dataManipulationTrack = 0;
                        loadSeasonStatus();
                        break;
                    }
                    case 8: {
                        openGUI();
                        break;
                    }
                    case 9: {
                        closeProgram();
                        break;
                    }
                    default: throw (new NumberFormatException()); //throws an error if options dont match
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("\nOOPS THAT'S AN ERROR- Input choice unacceptable\n Your input, "+option+" is not a choice we expected." +
                        " Please select your choice from the main menu and enter the appropriate key.\n");
            }
        }while (true); // runs infinitely till a proper input is received, where case statements break the loop
    }

    public static void addClub(){
        /* creating repeated variables outside all loops to minimize garbage collection */
        int option = 0;
        String finalName = "";
        String finalLocation = "";
        String finalDateOfFormation = "";
        String finalCoach = "";
        SportsClub club = null; // null pointer exception won't be thrown because input validation will prevent passing null into the premier league manager

        System.out.println("\n\nADDING A NEW CLUB TO THE PREMIER LEAGUE OF 2020/21 ~~~\n");

        if (premierLeagueService.getClubAvailability()) { // checking if anymore clubs can be registered to the premier league
            successfulRegistration: do {
                // system allows to register clubs with existing scores, or registration of new clubs
                System.out.print("Do you wish to add:\n\t[1] - Add new club to Premier League \n\t[2] - Add existing club with existing scores\nEnter your choice([x]): ");
                String stringOption = input.nextLine();

                try {
                    if (stringOption.equals("q") || stringOption.equals("Q")) {
                        System.out.println("\n\t\tSPONTANEOUS EXIT ACTIVATED\nRedirecting you Home ~~~");
                        break; // breaks infinite loop and flows through the normal function exit
                    }
                    option = Integer.parseInt(stringOption); // parsing the user input to integer, to make it easier for error throwing

                    if (option == 1 | option == 2) {
                        // ^ validating integer input (both selections will take in the normal data ie. name, location, ect.)
                        System.out.println("\nNEW CLUB REGISTRATION:\tEnter the following information ~~");
                        do { // getting club name with validations
                            try {
                                System.out.print("\n\tClub name: ");
                                String name = input.nextLine().trim();
                                if ((name.matches("^[a-zA-Z\\s\"'.]+")) && (!name.isEmpty())) { //regex validation to make sure no special characters are present
                                    finalName = premierLeagueService.compareName(name);
                                    // ^ comparing name with names existing, since name duplication is not allowed. If exists, throws name match exception, or returns name
                                    System.out.println("\n\t\t"+"\u2713"+" Club Name: Utilizable ~\n");
                                    break;
                                } else throw (new InputFormatError("name")); //ref custom errors
                            } catch (InputFormatError | NameMatchException e) { e.printStackTrace(); }
                        } while (true);

                        do { // getting club location with validation
                            try {
                                System.out.print("\tClub location: \n\t\tCity: ");
                                String city = input.nextLine().trim();
                                System.out.print("\t\tCountry: ");
                                String country = input.nextLine().trim();
                                if (!city.isEmpty() && !country.isEmpty() && city.matches("^[a-zA-Z\\s\"'.0-9]+") && country.matches("^[a-zA-Z\\s\"'.0-9]+")) {
                                    // ^ regex validation for location to make only specific special characters are used
                                    finalLocation = city + ", " + country;
                                    System.out.println("\n\t\t"+"\u2713"+" Club Location: Utilizable ~\n");
                                    break;
                                } else throw (new InputFormatError("location"));
                            } catch (InputFormatError e) { e.printStackTrace(); }
                        } while (true);

                        do { // getting club formation date with validation
                            try {
                                System.out.print("\tClub formation date: \n\t\tDay: ");
                                int day = Integer.parseInt(input.nextLine().trim());
                                System.out.print("\t\tMonth: ");
                                int month = Integer.parseInt(input.nextLine().trim());
                                System.out.print("\t\tYear: ");
                                String year = input.nextLine().trim(); //getting year in string to enable to compare to regex
                                finalDateOfFormation = DateUtility.produceDate(year, month, day); //using dateUtility to generate date
                                System.out.println("\n\t\t"+"\u2713"+" Club Date of Formation: Utilizable ~\n");
                                break;
                            } catch (InputFormatError e) { e.printStackTrace();
                            } catch (NumberFormatException e) { // where a string to integer conversion fails
                                System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nDate, month and year can only have numerics. Please reconsider your input.\n");
                            }
                        } while (true);

                        do { // getting coach name with validation
                            try {
                                System.out.print("\tClub coach name: ");
                                String coach = input.nextLine().trim();
                                if (coach.matches("^[a-zA-Z\\s\"'.0-9]+") && !coach.isEmpty()) { //validation with regex to ensure no special chars
                                    finalCoach = coach;
                                    System.out.println("\n\t\t"+"\u2713"+" Club Coach Name: Utilizable ~\n");
                                    break;
                                } else throw (new InputFormatError("coach"));
                            } catch (InputFormatError e) { e.printStackTrace(); }
                        } while (true);

                    } else throw (new OptionSelectionError());

                    switch (option) { //switch where the user picks to either register a new club or an existing club
                        case 1: { //registration of --NEW-- club
                            System.out.println("\nHere's what we understood.\n\tClub name = " + finalName + "\n\tClub Location = " +
                                    finalLocation + "\n\tClub date of Formation: " + finalDateOfFormation + "\n\tClub Coach: " + finalCoach);
                            // ^ displaying all received info for verification
                            club = new FootballClub(finalName, finalLocation, finalDateOfFormation, finalCoach);
                            // ^ assigning into temporary club till user confirms in next prompt
                            break;
                        }

                        case 2: { //registration of --EXISTING-- club
                            int finalWins = 0;
                            int finalDraws = 0;
                            int finalDefeats = 0;
                            int finalGoalsScored = 0;
                            int finalGoalsConceded = 0;
                            int holder = 0;
                            do { //getting the number of wins
                                System.out.print("SCORES ENTRY ~\n");
                                try {
                                    System.out.print("\tNumber of Wins: ");
                                    holder = Integer.parseInt(input.nextLine().trim());
                                    if (holder >= 0) finalWins = holder; else throw (new InputFormatError("matchesInput"));
                                    break;
                                } catch (NumberFormatException e) { System.out.println("\nOOPS THAT'S AN ERROR- Input type error\nWins can only be numerics. Please reconsider your input.\n");
                                } catch (InputFormatError e) { e.printStackTrace(); }
                            } while (true);

                            do { // getting the number of draws
                                try {
                                    System.out.print("\n\tNumber of Draws: ");
                                    holder = Integer.parseInt(input.nextLine().trim());
                                    if (holder >= 0) finalDraws = holder; else throw (new InputFormatError("matchesInput"));
                                    break;
                                } catch (NumberFormatException e) { System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nWins can only be numerics. Please reconsider your input.\n");
                                } catch (InputFormatError e) { e.printStackTrace(); }
                            } while (true);

                            do { // getting the number of defeats
                                try {
                                    System.out.print("\n\tNumber of Defeats: ");
                                    holder = Integer.parseInt(input.nextLine().trim());
                                    if (holder >= 0) finalDefeats = holder; else throw (new InputFormatError("matchesInput"));
                                    break;
                                } catch (NumberFormatException e) { System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nWins can only be numerics. Please reconsider your input.\n");
                                } catch (InputFormatError e) { e.printStackTrace(); }
                            } while (true);

                            do {// getting the number of goals scored
                                try {
                                    System.out.print("\n\tNumber of Goals scored: ");
                                    holder = Integer.parseInt(input.nextLine().trim());
                                    if (holder >= 0) finalGoalsScored = holder; else throw (new InputFormatError("matchesInput"));
                                    break;
                                } catch (NumberFormatException e) { System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nWins can only be numerics. Please reconsider your input.\n");
                                } catch (InputFormatError e) { e.printStackTrace(); }
                            } while (true);

                            do { // getting the number of goals conceded
                                try {
                                    System.out.print("\n\tNumber of Goals conceded: ");
                                    holder = Integer.parseInt(input.nextLine().trim());
                                    if (holder >= 0) finalGoalsConceded = holder; else throw (new InputFormatError("matchesInput"));
                                    break;
                                } catch (NumberFormatException e) { System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nWins can only be numerics. Please reconsider your input.\n");
                                } catch (InputFormatError e) { e.printStackTrace(); }
                            } while (true);

                            System.out.println("\nHere's what we understood.\n\tClub name: " + finalName + "\n\tClub Location: " +
                                    finalLocation + "\n\tClub date of Formation: " + finalDateOfFormation + "\n\tClub Coach: " +
                                    finalCoach + "\n\tNumber of Wins: " + finalWins + "\n\tNumber of Draws: " + finalDraws +
                                    "\n\tNumber of Defeats: " + finalDefeats + "\n\tNumber of Goals Scored: " + finalGoalsScored +
                                    "\n\tNumber of goals Conceded: " + finalGoalsConceded);
                            // ^ displaying all received info
                            club=new FootballClub(finalName, finalLocation, finalDateOfFormation, finalCoach, finalWins, finalDraws, finalDefeats, finalGoalsScored, finalGoalsConceded);
                            // ^ assigning into temporary club till user confirms in next prompt
                        }
                    }

                    String preference = "";
                    do { //making sure the user wants to register the club the data
                        System.out.print("\nDo you wish to register the club? You will NOT be able to edit these information later.\n\t" +
                                "[1] - Yes, Register the club locally\n\t[2] - No, do Not register the club\nEnter Choice([x]): ");
                        preference = input.nextLine().trim(); //confirming club registration with user
                        switch (preference) {
                            case "1":  //dont save or register
                                premierLeagueService.addClub(club); //creating new club
                                System.out.println("\n" + "\u2713" + "CLUB REGISTRATION -  SUCCESSFUL \nThe club has been registered locally. " +
                                        "If you do not save your changes, the club will not be added to the Premier League. Please remember to save your changes to the file before leaving!" +
                                        "\n\nRedirecting you Home ~~~\n");
                                dataManipulationTrack++;
                                break successfulRegistration; // breaks the main loop if successful

                            case "2": //register dont save
                                System.out.println("CLUB REGISTRATION - FAILED \n\nRedirecting you Home ~~~\n");
                                break successfulRegistration; // breaks the main loop if successful

                            default:
                                System.out.println("\nSorry, we didn't get that");
                                break;
                        }
                    } while (true);

                } catch (OptionSelectionError e) {
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("\nOOPS THAT'S AN ERROR- We expected a number \nThe value you entered for the is a string. Please input a valid choice.\n");
                } catch (Exception e) {
                    System.out.println("\nOOPS THAT'S AN ERROR- We encountered an unexpected error \nPlease re-run the program and make sure you haven't removed any system files.\n");
                }
            } while (true);
        }else System.out.println("\nOOPS THAT'S AN ERROR- Maximum clubs reached\nPremier League only allows 20 clubs to participate in the league.\n" +
                "Since all 20 clubs have already been registered, we are unable to register anymore clubs\n");
        // ^ Limiting the clubs to a maximum of 20 registrations
        home();
    }

    public static void removeClub() {
        System.out.println("\n\nDELETE A CLUB FROM THE PREMIER LEAGUE 2020/21 ~~~\n");
        if (((PremierLeagueManager) premierLeagueService).clubsIsEmpty()) { // checking if there are any clubs to delete
            System.out.println("No clubs to process a delete function!\nMake sure you have extracted existing information from the external file using Option 7 in the main menu.\n");
        } else {
            System.out.println("Do you wish to\n\t[1] - Delete club by search\n\t[2] - Relegate for the season\nChoice ([x]): ");
            String deletePreference = input.nextLine();
            if (deletePreference.equals("1")) {
                mainLoop:
                do {
                    System.out.print("\tEnter the name of the club you wish to search for to delete: ");
                    String name = input.nextLine().trim();
                    if (name.equals("q") || name.equals("Q")) { // exiting using q
                        System.out.println("\n\t\tSPONTANEOUS EXIT ACTIVATED\nRedirecting you Home ~~~");
                        break mainLoop; //exits the main loop
                    }
                    try {
                        premierLeagueService.compareName(name); //if name is available, throws exception
                    } catch (NameMatchException e) { // if exception thrown matches
                        premierLeagueService.removeClub(name); //runs remove method
                        break mainLoop; //exits the main loop and exits function
                    }

                    System.out.print("\nWe did not find a match for your input. Please make sure you entered the proper spacings\n");
                    // ^ in the case the club was not found in the search
                    failLoop:
                    do {
                        try {
                            System.out.print("Would you like to:\n\t[1] - Re-try\n\t[2] - Return to main menu\nEnter your choice: ");
                            String preference = input.nextLine().trim(); // asking the user if they want to go back to the menu or to retry the delete function
                            switch (preference) {
                                case ("1"): { // to retry- entering a name
                                    System.out.println("\nRedirecting you to the delete function ~~~\n");
                                    removeClub();
                                    dataManipulationTrack++;
                                    break failLoop;
                                }
                                case ("2"): { // to move back home
                                    break mainLoop;
                                }
                                default:
                                    throw (new OptionSelectionError());
                            }
                        } catch (OptionSelectionError | NumberFormatException e) {
                            System.out.println("\nERROR- Sorry we didn't get that.\n");
                        }
                    } while (true);
                } while (true);
            } else if (deletePreference.equals("2")) {
                int relegatePreference;
                do {
                    try {
                        if (((PremierLeagueManager) premierLeagueService).clubsListSize() >= 3) { // only allows to relegate if the list has at least 3 clubs
                            System.out.print("Are you sure you want to relegate for the season? This will remove the clubs with the least scores" +
                                    " from the premier league. Their scores nor data will be retrievable.\n\t[1] - Yes, relegate for the season\n\t[2] - No, take me back to Home Menu\nChoice ([x]): ");
                            relegatePreference = Integer.parseInt(input.nextLine());
                            if (relegatePreference == 1) { // prefer to save and exit application
                                List<SportsClub> relegatedClubs = ((PremierLeagueManager) premierLeagueService).getRelegateClubs(); // getting the clubs to be relegated
                                ((PremierLeagueManager) premierLeagueService).relegate(); // relegating the clubs with lowest scores
                                System.out.println("\n\t\t"+"\u2713"+" Relegation status: Successful ~\n\n" +
                                        "\tRelegated Clubs:\n\t\t1) "+relegatedClubs.get(0).getName()+" from "+relegatedClubs.get(0).getLocation()+
                                        "\n\t\t2) "+relegatedClubs.get(1).getName()+" from "+relegatedClubs.get(1).getLocation());
                                break;
                            }
                            if (relegatePreference==2) break; // prefer to go back to home
                        } else if (((PremierLeagueManager) premierLeagueService).clubsListSize() < 3) {
                            System.out.println("Sorry, relegation can NOT be performed yet for the season, since the premier league only has less than 3 registered clubs. " +
                                    "\nInorder to perform a relegation, at least 3 clubs must be registered in the season.");
                            break;
                        }
                        throw (new OptionSelectionError());
                    } catch (NumberFormatException e) {
                        System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nWins can only be numerics. Please reconsider your input.\n");
                    } catch (OptionSelectionError e){ e.printStackTrace(); }
                } while (true);
            }
        }
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

    public static void printTable() {
        System.out.println("\n\nPREMIER LEAGUE STATISTICS VIEW ~~~\n");
        premierLeagueService.printTable(); // accessing print function in premier league manager
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

    public static void playMatch() {
        System.out.println("\n\nADD A NEW PLAYED MATCH ~~~\n");
        if (((PremierLeagueManager) premierLeagueService).clubsListSize() < 2) {
            // ^ checking if the club list is empty or at least 2 clubs are registered to play a match
            System.out.println("\nNot enough registered clubs to play matches.\nA minimum of 2 clubs are required to be registered to play a match.\n");
        } else if (!premierLeagueService.getMatchAvailability()) { // checking matches are less than 360
            System.out.println("\nThe total number of matches are already met. Cannot play anymore matches.\n");
        } else {
            String finalDate = "";
            String finalClubOne = "";
            String finalClubTwo = "";
            int finalTeamOneScore = 0;
            int finalTeamTwoScore = 0;
            successfulCriteria: do {
                do { //Date detail entry
                    try {
                        System.out.print("\tMatch date:\n\t\tDay: ");
                        int day = Integer.parseInt(input.nextLine().trim());
                        System.out.print("\t\tMonth: ");
                        int month = Integer.parseInt(input.nextLine().trim());
                        System.out.print("\t\tYear: ");
                        String year = input.nextLine().trim();
                        if (year.equals("q") || year.equals("Q")) {
                            System.out.println("\n\t\tSPONTANEOUS EXIT ACTIVATED\nRedirecting you Home ~~~");
                            break successfulCriteria; // breaks infinite loop and flows through the normal function exit
                        }
                        finalDate = DateUtility.produceDate(year, month, day); // getting correct date using utility
                        break; //breaks loop since is exception is thrown, would be already broken
                    } catch (InputFormatError e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) { // where a string to integer conversion fails
                        System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nDate, month and year can only have numerics. " +
                                "Please reconsider your input.\n");
                    }
                } while (true);
                System.out.println("\n\t\t" + "\u2713" + " Match play date: Utilizable ~\n");
                do { // first club detail entry
                    System.out.print("\tName of Home Club (Club One): ");
                    String name = input.nextLine().trim();
                    try {
                        premierLeagueService.compareName(name); //compares the name to see if club exists
                    } catch (NameMatchException e) { //if exception is thrown, club exists, if not, isn't registered
                        finalClubOne = name;
                        break;
                    }
                    System.out.print("\nOOPS THAT'S AN ERROR~ Club not found\nWe did not find a match for your input. Please check the club name again.\n");
                } while (true);
                System.out.println("\n\t\t" + "\u2713" + " Home team name search status: Successful ~\n");
                do { //second club detail entry
                    System.out.print("\tName of Away Club (Club Two): ");
                    String name = input.nextLine().trim();
                    try {
                        premierLeagueService.compareName(name); //compares the name to see if club exists
                    } catch (NameMatchException e) { //if exception is thrown, club exists, if not, isn't registered
                        finalClubTwo = name;
                        break;
                    }
                    System.out.print("\nOOPS THAT'S AN ERROR~ Club not found\nWe did not find a match for your input. Please check the club name again.\n");
                } while (true);
                System.out.println("\n\t\t" + "\u2713" + " Away team name search status: Successful ~\n");
                do {// getting the number of goals scored for team one
                    try {
                        System.out.print("\tNumber of goals scored for home team " + finalClubOne + ": ");
                        int score = Integer.parseInt(input.nextLine().trim()); //exception handled
                        if (score >= 0) finalTeamOneScore = score;
                        else throw (new InputFormatError("matchesInput")); //makes sure score is positive
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nScore can only be numerics. Please reconsider your input.\n");
                    } catch (InputFormatError e) {
                        e.printStackTrace();
                    }
                } while (true);
                System.out.println("\n\t\t" + "\u2713" + " Home team score : Utilizable ~\n");
                do {// getting the number of goals scored for team two
                    try {
                        System.out.print("Number of goals scored for team two: ");
                        int score = Integer.parseInt(input.nextLine().trim());
                        if (score >= 0) finalTeamTwoScore = score;
                        else throw (new InputFormatError("matchesInput"));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nScore can only be numerics. Please reconsider your input.\n");
                    } catch (InputFormatError e) {
                        e.printStackTrace();
                    }
                } while (true);
                System.out.println("\n\t\t" + "\u2713" + " Away team score : Utilizable ~\n");

                // confirming user inputs by showing it again
                System.out.println("\nHere's what we got:\n\tDate of match: " + finalDate + "\n\tClub One: " + finalClubOne + "\n\tClub Two: " + finalClubTwo + "\n\tTeam One's score: " + finalTeamOneScore + "\n\tTeam Two's score: " + finalTeamTwoScore);
                String preference = "";
                successfulMatch: do {
                    System.out.print("\nDo you wish to save the match in the Premier League? You will NOT be able to edit these information later.\n\t" +
                            "[1] - Yes, Save match to the Premier League Locally\n\t[2] - No, Do not save the match\nEnter Choice([x]): ");
                    preference = input.nextLine().trim(); //confirming match saving with user
                    switch (preference) {
                        case "1":  //save match
                            try {
                                String returnString = premierLeagueService.playMatch(finalDate, finalClubOne, finalClubTwo, finalTeamOneScore, finalTeamTwoScore);
                                switch (returnString) {
                                    case "one": // if return is one, HOME team wins
                                        System.out.println("Match results added successfully\nResults\n\tWin:    " + finalClubOne + "- Three points\n\tDefeat: " + finalClubTwo + "- Nil points");
                                        break;
                                    case "two": // if return is two, AWAY team wins
                                        System.out.println("Match results added successfully\nResults\n\tWin:    " + finalClubTwo + "- Three points\n\tDefeat- " + finalClubOne + "- Nil points");
                                        break;
                                    case "draw": // if return is draw, the match is a draw
                                        System.out.println("Match results added successfully\nResults\n\tDraw match- 1 point each");
                                        break;
                                }
                            } catch (DateError e) {
                                e.printStackTrace();
                                break successfulMatch; // making the loop to iterate again to get inputs
                            } catch (Exception e) {
                                break successfulMatch;
                            }
                            System.out.println("\n" + "\u2713" + "MATCH PLAY SAVE -  SUCCESSFUL (LOCALLY) \nThe match has been saved locally. " +
                                    "If you do not save your changes, the match will not be updated into the Premier League. Please remember to save your changes to the file before leaving!" +
                                    "\n\nRedirecting you Home ~~~\n");
                            dataManipulationTrack++; // adding to the dataManipulationTrack since the data is manipulated when removed
                            break successfulCriteria; // breaks the main loop if successful

                        case "2":  //dont save match
                            System.out.println("MATCH PLAY SAVE - FAILED \n\nRedirecting you Home ~~~\n");
                            break successfulCriteria; // breaks the main loop if successful
                        default:
                            System.out.println("\nSorry, we didn't get that");
                            break;
                    }
                } while (true);
            } while (true);
        }
        home();
    }

    public static void viewClub() {
        System.out.println("\n\nVIEW AN EXISTING CLUB FROM THE PREMIER LEAGUE ~~~\n");
        if (((PremierLeagueManager) premierLeagueService).clubsIsEmpty()) { // checking if clubs are available to view
            System.out.println("No clubs to process a view function!\nMake sure you have extracted existing information from the external file using Option 7 in the main menu.\n");
        } else {
            viewMainLoop: do {
                System.out.print("\tEnter the name of the club you wish to search for: ");
                String name = input.nextLine().trim(); // removing both spaces on the sides
                if (name.equals("q") || name.equals("Q")) {
                    System.out.println("\n\t\tSPONTANEOUS EXIT ACTIVATED\nRedirecting you Home ~~~");
                    break viewMainLoop; // breaks infinite loop and flows through the normal function exit
                }
                try {
                    premierLeagueService.compareName(name); // checks if name is available in the premier league
                } catch (NameMatchException e) { // if found, exception thrown
                    premierLeagueService.viewClub(name);
                    break;
                }
                System.out.print("\nWe did not find a match for your input. \nPlease make sure you entered the proper capitalization's\n");
                retryLoop: do { // < iterates till the user input is right
                    try {
                        System.out.print("Would you like to:\n\t[1] - Re-try searching for a club\n\t[2] - Return to main menu\nChoice ([x]): ");
                        String preference = input.nextLine().trim(); // confirming view search
                        switch (preference) {
                            case ("1"): { // to retry the search
                                System.out.println("\nRedirecting you to the view component ~~~\n");
                                break retryLoop;
                            }
                            case ("2"): { // to move back home
                                break viewMainLoop;
                            }
                            default: throw (new OptionSelectionError()); // if both have failed, obviously, the option is wrong
                        }
                    } catch (OptionSelectionError e) {
                        e.printStackTrace();
                    }
                } while (true); // retryLoop - exits in the
            } while (true); // viewMainLoop
        }
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

    public static void saveSeasonStatus() {
        System.out.println("\nSAVING THE CURRENT CHANGES TO THE FILE ~~~");
        try {
            premierLeagueService.saveSeasonStatus(); // saving in new file or overriding existing
            System.out.println("\n\t"+"\u2713\t"+" Save Status: Successful ~");
        }catch (NullPointerException e) {
            System.out.println("\nNo data to save!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nOOPS THAT'S AN ERROR- File unavailable\n" +
                    "\nWe encountered an unexpected error. Please try again");
        }
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

    public static void loadSeasonStatus() {
        System.out.println("\nRETRIEVING THE DATA FROM THE FILE ~~~");
        try {
            premierLeagueService.loadSeasonStatus(); //throws exception
        }catch (FileNotFoundException e){
            System.out.println("\nOOPS THAT'S AN ERROR- File unavailable\n" +
                    "If you are running this program for the first time, you need to save changes to the file first using menu Option 6.\n" +
                    "If not, the log file has disappeared from the system. Please make sure you did not move any files from the folder" +
                    " dedicated for this program.");
        } catch (Exception e) { // any other errors throwable
            System.out.println("\nOOPS THAT'S AN ERROR- File unavailable\n" +
                    "\nWe encountered an unexpected error. Please try again");
        }
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

    public static void openGUI() {
        System.out.println("\nOPENING GRAPHICAL USER INTERFACE THROUGH THE CONSOLE APPLICATION ~~~");
        do {
            try {
                int loadPreference;
                if (dataManipulationTrack > 0) { // if changed have been made locally, will be updated in the manipulationTrack variable
                    System.out.print("You have to save your current changes before opening the Application. Do you wish to save and proceed?\n\t[1] - Yes, Save and open GUI\n\t[2] - No, Don't open GUI\nChoice: ");
                    loadPreference = Integer.parseInt(input.nextLine());
                } else loadPreference = 1;
                if (loadPreference == 1) { // if selects to save and open GUI
                    System.out.print("Would you like to open the\n\t[1] - Web-based Application (Angular-Play-Java)\n\t" +
                            "[2] - Desktop Application (Java-FX)\nChoice ([x]): ");
                    int guiPreference = Integer.parseInt(input.nextLine());
                    if (dataManipulationTrack > 0) {
                        premierLeagueService.saveSeasonStatus(); // saving status and clearing premier league lists
                    }
                    dataManipulationTrack = 0;
                    if (guiPreference == 1) { // web-application
                        try {
                            Runtime.getRuntime().exec("cmd /user:Administrator start cmd.exe /K "+"for /f \"tokens=5\" %a in ('netstat -ano ^| find \"4200\" ^| find \"LISTENING\"') do taskkill /f /pid %a");
                            // ^ starts a cmd and clear the 4200 port of the computer. closes once complete
                            Runtime.getRuntime().exec("cmd /user:Administrator start cmd.exe /K "+"for /f \"tokens=5\" %a in ('netstat -ano ^| find \"9000\" ^| find \"LISTENING\"') do taskkill /f /pid %a");
                            // ^ starts a cmd and clears the 9000 port of the computer. closes once complete
                        } catch (Exception e) {
                            System.out.println("\nWe encountered an unexpected problem. Please make sure the files are installed properly.\n");
                            break;
                        }
                        Process sbtProcess = Runtime.getRuntime().exec("cmd /c start/wait cmd.exe /K " + "sbt run");
                        // ^ opens a cmd and commands SBT RUN to start localhost 9000 and 4200 (backend serve and angular serve)
                        System.out.println("\nSetting up and opening Premier League website: http://localhost:4200/\n\n" +
                                "~~Please close the external terminal opened to return control to the console~~\n\n" +
                                "Switching control to the external terminal..\nConsole will wait for control..");
                        // once sbt run is successful, the console will freeze till the external cmd is closed
                        //      this is to ensure the data can be loaded to the console again once changes are made in the GUI
                        sbtProcess.waitFor();
                        try {
                            Runtime.getRuntime().exec("cmd /user:Administrator start cmd.exe /K "+"for /f \"tokens=5\" %a in ('netstat -ano ^| find \"4200\" ^| find \"LISTENING\"') do taskkill /f /pid %a");
                            Runtime.getRuntime().exec("cmd /user:Administrator start cmd.exe /K "+"for /f \"tokens=5\" %a in ('netstat -ano ^| find \"9000\" ^| find \"LISTENING\"') do taskkill /f /pid %a");
                            // clears both ports again at the time of closing
                        } catch (Exception e) {
                            System.out.println("\nWe encountered an unexpected problem. Please make sure the files are installed properly.\n");
                            break;
                        }
                        sbtProcess.destroy(); // process is finally destroyed
                        System.out.println("\nConsole repowered.\n");
                        premierLeagueService.loadSeasonStatus(); // loads the changed done during using the GUI
                        break;
                    }
                    if (guiPreference == 2) { // javafx option
                        GraphicalApplicationJavaFX.launch(GraphicalApplicationJavaFX.class); // opens javafx
                        premierLeagueService.loadSeasonStatus();
                        break;
                    }
                    throw (new OptionSelectionError());
                }
                if (loadPreference == 2) break; // if selects to go back to home
                throw (new OptionSelectionError()); // since the loop will be broken if selections are complete, throws an error at the end

            } catch (NumberFormatException e) {
                System.out.println("\nOOPS THAT'S AN ERROR- Input type error \nOptions can only be numerics. Please reconsider your input.\n");
            } catch (OptionSelectionError e) {
                e.printStackTrace();
            } catch (Exception e) {
                    System.out.println("OOPS THAT'S AN ERROR- We encountered an unexpected problem when re-loading the file data. \n" +
                            "Please restart the application.");
                    break;
            }
        } while (true);
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

    public static void closeProgram() {
        System.out.println("\nCLOSING PREMIER LEAGUE CONSOLE APPLICATION ~~~\n");
        do {
            try {
                int exitPreference;
                if (dataManipulationTrack > 0) { // if changed have been made locally, will be updated in the manipulationTrack variable
                    System.out.print("Do you wish to save the changes you made to the file?\n\t[1] - Yes, save current changes and exit application\n\t" +
                            "[2] - No, exit application WITHOUT saving changes\n\t[3] - Go back to Menu\nChoice ([x]): ");
                    exitPreference = Integer.parseInt(input.nextLine());
                } else exitPreference = 2;
                if (exitPreference == 1) { // prefer to save and exit application
                    premierLeagueService.saveSeasonStatus();
                    System.out.println("\nThank you for using the Premier League Console Application!\nSystem.ded -.-");
                    System.exit(10);
                }
                if (exitPreference==2) {
                    System.out.println("\nThank you for using the Premier League Console Application!\nSystem.ded -.-");
                    System.exit(20);// prefer to exit application without saving
                }
                if (exitPreference==3) break; // prefer to go back to home
                throw (new OptionSelectionError());
            } catch (NumberFormatException e) {
                System.out.println("\nOOPS THAT'S AN ERROR- Input type error\nPlease reconsider your input.\n");
            } catch (OptionSelectionError e) {
                e.printStackTrace();
            } catch (NullPointerException ignored) {
            } catch (Exception e) {
                System.out.println("OOPS THAT'S AN ERROR- Unexpected error\nThis maybe if the log files have been deleted from the ");
            }
        } while (true);
        System.out.println("\nRedirecting you Home ~~~\n");
        home();
    }

}
