package services;

import entities.SportsClub;
import utilities.customErrors.NameMatchException;

public interface LeagueManager {

    void addClub(SportsClub club);

    void removeClub(String name);

    void printTable();

    String playMatch(String date, String teamOne, String teamTwo, int scoreTeamOne, int scoreTeamTwo) throws Exception;

    void viewClub(String name);

    void saveSeasonStatus() throws Exception;

    void loadSeasonStatus() throws Exception;

    String compareName(String Name) throws NameMatchException;

    boolean getClubAvailability();

    boolean getMatchAvailability();

}
