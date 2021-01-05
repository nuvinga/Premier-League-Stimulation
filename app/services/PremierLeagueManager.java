package services;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import entities.Match;
import entities.SportsClub;
import utilities.customErrors.DateError;
import utilities.customErrors.NameMatchException;
import utilities.customErrors.OptionSelectionError;
import entities.Football.FootballClub;

public class PremierLeagueManager implements services.LeagueManager {

    private static final int MAXIMUM_CLUBS = 20; // used to regulate the number of clubs
    private static final int MAXIMUM_MATCHES = 380; // used to regulate the number of matches
    private static int clubSlotsAvailable = MAXIMUM_CLUBS; // used to keep track of clubs registrations available
    private static int matchesAvailable = MAXIMUM_MATCHES; // used to keep track of match slots available
    private static LeagueManager instance = null; // league manager instance
    private static final List<SportsClub> premierLeagueClubs = new ArrayList<>(); // list of clubs registered
    private static final List<Match> premierLeagueMatches = new ArrayList<>(); // list of matches played
    private static final Scanner input = new Scanner(System.in); // scanner object

    @Override
    public void addClub(SportsClub club) {
        premierLeagueClubs.add(club); // registering a new club to the premier league
        clubSlotsAvailable--; // reducing the available club registrations by one
        System.out.println( clubSlotsAvailable > 0 ? ("\n"+clubSlotsAvailable+" club spots available in the league") :
                "And, that's all the clubs for the season" ); // prompting the available slots of clubs
    }

    @Override
    public void removeClub(String name) {
        List<Integer> equalsClubsIndexes = new ArrayList<>();
        System.out.println("\nList of similar results: ");
        for (int i = 0; i < premierLeagueClubs.size(); i++) { // traversing the clubs list to compare to name
            if (premierLeagueClubs.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                equalsClubsIndexes.add(i); // if found, adds the list index to the index arraylist
                System.out.println("\t["+ (equalsClubsIndexes.size()) +"] - "+ premierLeagueClubs.get(i).getName()+" from "+ premierLeagueClubs.get(i).getLocation());
            }
        }
        String preference = "";
        int finalizedIndex = 0;
        int option = 0;
        do{
            System.out.print("Enter your choice: ");
            String stringOption = input.nextLine().trim(); // getting the users choice of club from choices
            try {
                option = Integer.parseInt(stringOption);
                if ((option <= equalsClubsIndexes.size()) && !(option <= 0)) { // if the option is not negative and is within the options
                    finalizedIndex = equalsClubsIndexes.get(option-1); // getting the club in the input index
                    break; // breaks the loop and continues the method
                } else throw (new OptionSelectionError()); // if the input is incorrect
            } catch (OptionSelectionError e){ e.printStackTrace();
            } catch (NumberFormatException e){
                System.out.println("\nOOPS THAT'S AN ERROR- Option selection unacceptable\nThe option you selected is a letter/character. Please review the options available and enter your digit choice.\n");
            }
        }while (true);
        System.out.println("\n"+ premierLeagueClubs.get(finalizedIndex).toString()); // showing data to confirm deletion
        do {
            System.out.print("\nAre you sure you want to delete this entry?\n\t[1] - Yes\n\t[2] - No\nChoice ([x]): ");
            preference = input.nextLine();
            if (preference.equals("1")) { // if confirmed
                premierLeagueClubs.remove(finalizedIndex); // removes entry in specific index
                clubSlotsAvailable++; // allows one more club to be added
                System.out.println("\n\t\t"+"\u2713"+" Club Deletion status: Successful ~\n\n");
                System.out.println("Please remember to save your changes to the file before leaving!\n");
                break;
            } else if (preference.equals("2")) {
                break;
            } else System.out.println("\nOOPS THAT'S AN ERROR- Option selection unacceptable\nThe option you selected is a letter/character. Please review the options available and enter your digit choice.\n");
        }while(true);
    }

    public void relegate() {
        premierLeagueClubs.sort(Collections.reverseOrder()); // sorting the list to get the clubs with least scores to the bottom
        premierLeagueClubs.remove(premierLeagueClubs.size()-2); // removing the club before the last
        premierLeagueClubs.remove(premierLeagueClubs.size()-1); // removing the last club
    }

    @Override
    public void printTable() {
        if (premierLeagueClubs.size()==0){
            System.out.println("\n\tNot data to process a table!\nMake sure you have extracted existing information from the external file using Option 7 in the main menu.");
        }else {
            int longestNameSpaces = 12; // minimum spacing set to 12 spacing
            int longestLocationSpaces = 12;
            int longestCoachSpaces = 12;
            List<FootballClub> tempList = new ArrayList<>();
            for (SportsClub club : premierLeagueClubs) {
                tempList.add((FootballClub) club);
                if (longestNameSpaces < (club.getName().length())) { //finding longest name and storing length for spacing
                    longestNameSpaces = club.getName().length() + 4; // adding four mandatory to have extra space
                }
                if (longestLocationSpaces < (club.getLocation().length())) { //finding longest location and storing length
                    longestLocationSpaces = club.getLocation().length() + 4;
                }
                if (longestCoachSpaces < (club.getCoach().length())) { // finding the longest coach name
                    longestCoachSpaces = club.getCoach().length() + 4;
                }
            }
            tempList.sort(Collections.reverseOrder()); // sorting clubs according to points and goals difference
            System.out.println("\nTABLE OF STATISTICS OF TEAMS OF THE PREMIER LEAGUE 2020");
            System.out.print("| ");
            System.out.printf("%" + -longestNameSpaces + "s", "Club Name ");
            System.out.print("| ");
            System.out.printf("%" + -longestLocationSpaces + "s", "Location  ");
            System.out.print("| Date Of Formation | ");
            System.out.printf("%" + -longestCoachSpaces + "s", "Coach  ");
            System.out.println("| Number of Wins | Number of Draws | Number of Defeats | Matches Played | Goals Conceded | Goals Scored" +
                    " | Goal Difference |  TOTAL POINTS  |");
            for (FootballClub footballClub : tempList) {
                System.out.print("| ");
                System.out.printf("%" + -longestNameSpaces + "s", footballClub.getName());
                System.out.print("| ");
                System.out.printf("%" + -longestLocationSpaces + "s", footballClub.getLocation());
                System.out.print("| ");
                System.out.printf("%" + -18 + "s", footballClub.getDateOfFormation());
                System.out.print("| ");
                System.out.printf("%" + -longestCoachSpaces + "s", footballClub.getCoach());
                System.out.print("| ");
                System.out.printf("%" + -15 + "s", footballClub.getNumWins());
                System.out.print("| ");
                System.out.printf("%" + -16 + "s", footballClub.getNumDraws());
                System.out.print("| ");
                System.out.printf("%" + -18 + "s", footballClub.getNumDefeats());
                System.out.print("| ");
                System.out.printf("%" + -15 + "s", footballClub.getMatchesPlayed());
                System.out.print("| ");
                System.out.printf("%" + -15 + "s", footballClub.getGoalsConceded());
                System.out.print("| ");
                System.out.printf("%" + -13 + "s", footballClub.getGoalsScored());
                System.out.print("| ");
                System.out.printf("%" + -16 + "s", footballClub.getGoalDifference());
                System.out.print("| ");
                System.out.printf("%" + -15 + "s", footballClub.getPoints());
                System.out.println("|");
            }
        }
    }

    @Override
    public String playMatch(String date, String teamOne, String teamTwo, int scoreTeamOne, int scoreTeamTwo) throws Exception{
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        FootballClub clubOne = null;
        FootballClub clubTwo = null;
        String returnString = "";
        Date dateClubOne = null;
        Date dateClubTwo = null;
        int clubOneIndex = 0;
        int clubTwoIndex = 0;
        for (int i = 0; i < premierLeagueClubs.size(); i++) { // looks for the string club name
            if (premierLeagueClubs.get(i).getName().toLowerCase().equals(teamOne.toLowerCase())) { // checks for team One
                clubOne = (FootballClub) premierLeagueClubs.get(i); // gets the premier league club and puts it into local variable
                dateClubOne = clubOne.getComparableDate(); // getting comparable date from club
                clubOneIndex = i; // saving index for future use
            } else if (premierLeagueClubs.get(i).getName().toLowerCase().equals(teamTwo.toLowerCase())) { // checks for team Two
                clubTwo = (FootballClub) premierLeagueClubs.get(i);
                dateClubTwo = clubTwo.getComparableDate();
                clubTwoIndex = i;
            }
        }
        if ((dateClubOne!=null && dateClubOne.before(format.parse(date)))&&(dateClubTwo!=null && dateClubTwo.before(format.parse(date)))) {
            // ^ if date of the clubs is not null, and the club is formed before intended match date
            Match match = new Match(date, clubOne, clubTwo, scoreTeamOne, scoreTeamTwo);
            premierLeagueMatches.add(match); // adds the match statistics to the premierLeagueMatches list
            matchesAvailable--; // removes one match slot
            switch (match.getResult()) {
                case "one": // if team one wins, returns string one
                    ((FootballClub) premierLeagueClubs.get(clubOneIndex)).addWin(scoreTeamOne, scoreTeamTwo);
                    ((FootballClub) premierLeagueClubs.get(clubTwoIndex)).addDefeat(scoreTeamTwo, scoreTeamOne);
                    // ^ uses the saved clubIndexes to access the list and update the wins and loses of the specific clubs
                    returnString= "one";
                    break;
                case "two": // if team two wins, returns string two
                    ((FootballClub) premierLeagueClubs.get(clubTwoIndex)).addWin(scoreTeamTwo, scoreTeamOne);
                    ((FootballClub) premierLeagueClubs.get(clubOneIndex)).addDefeat(scoreTeamOne, scoreTeamTwo);
                    returnString= "two";
                    break;
                case "draw": // if draw, returns string draw
                    ((FootballClub) premierLeagueClubs.get(clubOneIndex)).addDraw(scoreTeamOne, scoreTeamTwo);
                    ((FootballClub) premierLeagueClubs.get(clubTwoIndex)).addDraw(scoreTeamTwo, scoreTeamOne);
                    returnString= "draw";
                    break;
            }
            premierLeagueMatches.sort(Collections.reverseOrder()); // sorting the premier league match list again to date
        } else throw (new DateError()); // if the clubs are null or if before the club registration date, throws dateError
        return returnString;
    }

    @Override
    public void viewClub(String name) {
        FootballClub tempClub = null;
        List<Integer> equalsClubsIndexes = new ArrayList<>();
        int option = 0;
        System.out.println("\nList of similar results: ");
        for (int i = 0; i < premierLeagueClubs.size(); i++) {
            if (premierLeagueClubs.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                equalsClubsIndexes.add(i); // saving the index of the club for future use
                System.out.println("\t["+ (equalsClubsIndexes.size()) +"] - "+ premierLeagueClubs.get(i).getName()+" from "+ premierLeagueClubs.get(i).getLocation());
            }
        }
        do{
            System.out.print("Enter your choice: ");
            String stringOption = input.nextLine();
            try {
                option = Integer.parseInt(stringOption);
                if ((option <= equalsClubsIndexes.size()) && !(option <= 0)) { // if the option is not negative and is within the options
                    tempClub = (FootballClub) premierLeagueClubs.get(equalsClubsIndexes.get(option-1)); // getting the club the user chose
                    break;
                } else throw (new OptionSelectionError());
            } catch (OptionSelectionError e){ e.printStackTrace();
            } catch (NumberFormatException e){
                System.out.println("\nOOPS THAT'S AN ERROR- Option selection unacceptable\nThe option you selected is a letter/character. Please review the options available and enter your digit choice.\n");
            }
        }while (true);
        System.out.println("STATISTICS FOR FOOTBALL CLUB");
        System.out.println("  Club Name:                  "+tempClub.getName());
        System.out.println("  Club Location:              "+tempClub.getLocation());
        System.out.println("  Club Date of Formation:     "+tempClub.getDateOfFormation());
        System.out.println("  Club Coach:                 "+tempClub.getCoach());
        System.out.println("  Club Statistics ");
        System.out.println("      Number of Wins:         "+tempClub.getNumWins());
        System.out.println("      Number of Draws:        "+tempClub.getNumDraws());
        System.out.println("      Number of Defeats:      "+tempClub.getNumDefeats());
        System.out.println("      Total Matches Played:   "+tempClub.getMatchesPlayed());
        System.out.println("      Total Goals Scored:     "+tempClub.getGoalsScored());
        System.out.println("      Total Goals Conceded:   "+tempClub.getGoalsConceded());
        System.out.println("      Difference of Goals:    "+tempClub.getGoalDifference());
        System.out.println("      TOTAL NUMBER OF POINTS: "+tempClub.getPoints());
    }

    @Override
    public void saveSeasonStatus() throws Exception{
        if (!premierLeagueClubs.isEmpty()) {
            FootballClub tempClubHolder = null;
            Match tempMatchHolder = null;
            File file = new File("PremierLeagueApplicationLogs.txt"); // creates a file if not available
            FileOutputStream outStream = new FileOutputStream(file);
            ObjectOutputStream objOutStream = new ObjectOutputStream(outStream);
            objOutStream.writeInt(clubSlotsAvailable); // writes the clubs slots integer first
            objOutStream.writeInt(matchesAvailable); // writes the matches slots integer for use next time
            for (Match match : premierLeagueMatches) {
                tempMatchHolder = match;
                objOutStream.writeObject(tempMatchHolder); // writes each match to the file
            }
            for (SportsClub club : premierLeagueClubs) {
                tempClubHolder = (FootballClub) club;
                objOutStream.writeObject(tempClubHolder); // writes each club to the file
            }
            premierLeagueClubs.clear(); // clears the lists in the league manager
            premierLeagueMatches.clear();
            objOutStream.flush(); // flushing the data and closing the file
            objOutStream.close();
        }else throw new NullPointerException(); // if premier league is empty, throws null pointer exception
    }

    @Override
    public void loadSeasonStatus() throws Exception {
        File file = new File("PremierLeagueApplicationLogs.txt");
        FileInputStream inStream = new FileInputStream(file);
        ObjectInputStream objInStream = new ObjectInputStream(inStream);
        premierLeagueClubs.clear(); // clearing the lists to make sure data is over written
        premierLeagueMatches.clear();
        clubSlotsAvailable = objInStream.readInt(); // reading the club slots available
        matchesAvailable = objInStream.readInt(); // reading the match slots available
        for (int i=0; i<MAXIMUM_MATCHES-matchesAvailable;i++){
            // ^ reading each match for the difference of the Maximum matches and matched available
            premierLeagueMatches.add((Match) objInStream.readObject());
        }
        do {
            try {
                // reads all the other clubs till EOF
                premierLeagueClubs.add((FootballClub) objInStream.readObject());
            } catch (EOFException e) {
                objInStream.close();
                inStream.close();
                break;
            }
        }while (true);
    }

    @Override
    public String compareName(String name) throws NameMatchException {
        boolean nameComparator = false;
        for (SportsClub footballClub : premierLeagueClubs) {
            if (footballClub.getName().toLowerCase().equals(name.toLowerCase())) {
                nameComparator = true;
            }
        }
        if (!nameComparator){
            return name;
        }else{ // throws exception if name found
            throw (new NameMatchException());
        }
    }

    @Override
    public boolean getClubAvailability() { return clubSlotsAvailable > 0; }

    @Override
    public boolean getMatchAvailability() { return matchesAvailable >0; }

    public static LeagueManager getInstance() {
        if (instance == null) instance = new PremierLeagueManager();
        return instance;
    }

    public List<Match> getMatchesForDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = null;
        try {
            formatDate = format.parse(date);
        }catch (Exception ignored){ } // since the data from the match list is already validated and to format
        List<Match> returnMatches = new ArrayList<>();
        for (Match matches : premierLeagueMatches) {
            System.out.println(matches);
            if ((matches.comparableDate().equals(formatDate))) { // if the matches are equal to each other
                returnMatches.add(matches);
            }
        }
        return returnMatches;
    }

    public List<FootballClub> getFootballClubList() {
        List<FootballClub> returnClubs = new ArrayList<>();
        for (SportsClub club : premierLeagueClubs){
            returnClubs.add((FootballClub) club);
        }
        return returnClubs;
    }

    public int getOldestClubYear() {
        int oldestYear=10000;
        for (SportsClub club : premierLeagueClubs) {
            if (oldestYear > ((club.getComparableDate().getYear()))) {
                oldestYear = club.getComparableDate().getYear();
            }
        }
        return oldestYear+1900; // since year returns date from 1900
    }

    public boolean clubsIsEmpty(){
        return premierLeagueClubs.size()==0;
    }

    public boolean matchesIsEmpty(){
        return premierLeagueClubs.size()==0;
    }

    public int clubsListSize() {
        return premierLeagueClubs.size();
    }

    public int matchesListSize() {
        return premierLeagueMatches.size();
    }

    public List<SportsClub> getClubList() {
        return premierLeagueClubs;
    }

    public List<Match> getMatchList() {
        return premierLeagueMatches;
    }

    public List<SportsClub> getRelegateClubs() {
        premierLeagueClubs.sort(Collections.reverseOrder());
        List<SportsClub> returnList = new ArrayList<>();
        returnList.add((premierLeagueClubs.get(premierLeagueClubs.size()-2)));
        returnList.add((premierLeagueClubs.get(premierLeagueClubs.size()-1)));
        return returnList;
    }

}
