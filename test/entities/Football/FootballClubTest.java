package entities.Football;

import entities.SportsClub;
import org.junit.Test;

import static org.junit.Assert.*;

public class FootballClubTest {

    private final SportsClub testClub = new FootballClub("Man United", "Manchester, United",
            "02/01/2020", "J. F. Kennedy", 5,3,1,4,2);

    @Test
    public void addWin() {
        ((FootballClub) testClub).addWin(4,2);
        int actualNumWins = ((FootballClub) testClub).getNumWins();
        int actualMatchesPlayed = ((FootballClub) testClub).getMatchesPlayed();
        int actualGoalsScored = ((FootballClub) testClub).getGoalsScored();
        int actualGoalsConceded = ((FootballClub) testClub).getGoalsConceded();
        int actualGoalDifference = ((FootballClub) testClub).getGoalDifference();
        int actualPoints = ((FootballClub) testClub).getPoints();

        int expectedNumWins = 6;
        int expectedMatchesPlayed = 10;
        int expectedGoalsScored = 8;
        int expectedGoalsConceded = 4;
        int expectedGoalDifference = 4;
        int expectedPoints = 21;

        assertEquals(expectedNumWins, actualNumWins);
        assertEquals(expectedMatchesPlayed, actualMatchesPlayed);
        assertEquals(expectedGoalsScored, actualGoalsScored);
        assertEquals(expectedGoalsConceded, actualGoalsConceded);
        assertEquals(expectedGoalDifference, actualGoalDifference);
        assertEquals(expectedPoints, actualPoints);
        System.out.println("Add win test- passed");
    }

    @Test
    public void getNumWins() {
        int actualNumWins = ((FootballClub) testClub).getNumWins();
        int expectedNumWins = 5;
        assertEquals(expectedNumWins, actualNumWins);
    }

    @Test
    public void setNumWins() {
        ((FootballClub) testClub).setNumWins(10);
        int expectedNumWins = 10;
        assertEquals(expectedNumWins, ((FootballClub) testClub).getNumWins());
    }

    @Test
    public void addDraw() {
        ((FootballClub) testClub).addDraw(4,2);
        int actualNumDraws = ((FootballClub) testClub).getNumDraws();
        int actualMatchesPlayed = ((FootballClub) testClub).getMatchesPlayed();
        int actualGoalsScored = ((FootballClub) testClub).getGoalsScored();
        int actualGoalsConceded = ((FootballClub) testClub).getGoalsConceded();
        int actualGoalDifference = ((FootballClub) testClub).getGoalDifference();
        int actualPoints = ((FootballClub) testClub).getPoints();

        int expectedNumDraws = 4;
        int expectedMatchesPlayed = 10;
        int expectedGoalsScored = 8;
        int expectedGoalsConceded = 4;
        int expectedGoalDifference = 4;
        int expectedPoints = 19;

        assertEquals(expectedNumDraws, actualNumDraws);
        assertEquals(expectedMatchesPlayed, actualMatchesPlayed);
        assertEquals(expectedGoalsScored, actualGoalsScored);
        assertEquals(expectedGoalsConceded, actualGoalsConceded);
        assertEquals(expectedGoalDifference, actualGoalDifference);
        assertEquals(expectedPoints, actualPoints);
        System.out.println("Add draw test- passed");
    }

    @Test
    public void getNumDraws() {
        int actualNumDraws = ((FootballClub) testClub).getNumDraws();
        int expectedNumDraws = 3;
        assertEquals(expectedNumDraws, actualNumDraws);
    }

    @Test
    public void setNumDraws() {
        ((FootballClub) testClub).setNumDraws(10);
        int expectedNumDraws = 10;
        assertEquals(expectedNumDraws, ((FootballClub) testClub).getNumDraws());
    }

    @Test
    public void addDefeat() {
        ((FootballClub) testClub).addDefeat(4,2);
        int testNumDefeats = ((FootballClub) testClub).getNumDefeats();
        int testMatchedPlayed = ((FootballClub) testClub).getMatchesPlayed();
        int testGoalsScored = ((FootballClub) testClub).getGoalsScored();
        int testGoalsConceded = ((FootballClub) testClub).getGoalsConceded();
        int testGoalDifference = ((FootballClub) testClub).getGoalDifference();
        int testPoints = ((FootballClub) testClub).getPoints();

        int expectedNumDefeats = 2;
        int expectedMatchesPlayed = 10;
        int expectedGoalsScored = 8;
        int expectedGoalsConceded = 4;
        int expectedGoalDifference = 4;
        int expectedPoints = 18;

        assertEquals(testNumDefeats, expectedNumDefeats);
        assertEquals(testMatchedPlayed, expectedMatchesPlayed);
        assertEquals(testGoalsScored, expectedGoalsScored);
        assertEquals(testGoalsConceded, expectedGoalsConceded);
        assertEquals(testGoalDifference, expectedGoalDifference);
        assertEquals(testPoints, expectedPoints);
        System.out.println("Add draw test- passed");
    }

    @Test
    public void getNumDefeats() {
        int actualNumDefeats = ((FootballClub) testClub).getNumDefeats();
        int expectedNumDefeats = 1;
        assertEquals(expectedNumDefeats, actualNumDefeats);
    }

    @Test
    public void setNumDefeats() {
        ((FootballClub) testClub).setNumDefeats(10);
        int expectedNumDefeats = 10;
        assertEquals(expectedNumDefeats, ((FootballClub) testClub).getNumDefeats());
    }

    @Test
    public void getMatchesPlayed() {
        int actualMatchesPlayed = ((FootballClub) testClub).getMatchesPlayed();
        int expectedMatchesPlayed = 9;
        assertEquals(expectedMatchesPlayed, actualMatchesPlayed);
    }

    @Test
    public void setMatchesPlayed() {
        ((FootballClub) testClub).setMatchesPlayed(10);
        int expectedMatchesPlayed = 10;
        assertEquals(expectedMatchesPlayed, ((FootballClub) testClub).getMatchesPlayed());
    }

    @Test
    public void getGoalsScored() {
        int actualGoalsScored = ((FootballClub) testClub).getGoalsScored();
        int expectedGoalsScored = 4;
        assertEquals(expectedGoalsScored, actualGoalsScored);
    }

    @Test
    public void setGoalsScored() {
        ((FootballClub) testClub).setGoalsScored(10);
        int expectedGoalsScored = 10;
        assertEquals(expectedGoalsScored, ((FootballClub) testClub).getGoalsScored());
    }

    @Test
    public void getGoalsConceded() {
        int actualGoalsConceded = ((FootballClub) testClub).getGoalsConceded();
        int expectedGoalsConceded = 2;
        assertEquals(expectedGoalsConceded, actualGoalsConceded);
    }

    @Test
    public void setGoalsConceded() {
        ((FootballClub) testClub).setGoalsConceded(10);
        int expectedGoalsConceded = 10;
        assertEquals(expectedGoalsConceded, ((FootballClub) testClub).getGoalsConceded());
    }

    @Test
    public void getGoalDifference() {
        int actualGoalDifference = ((FootballClub) testClub).getGoalDifference();
        int expectedGoalDifference = 2;
        assertEquals(expectedGoalDifference, actualGoalDifference);
    }

    @Test
    public void setGoalDifference() {
        ((FootballClub) testClub).setGoalDifference(10);
        int expectedGoalDifference = 10;
        assertEquals(expectedGoalDifference, ((FootballClub) testClub).getGoalDifference());
    }

    @Test
    public void getPoints() {
        int actualPoints = ((FootballClub) testClub).getPoints();
        int expectedPoints = 18;
        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    public void setPoints() {
        ((FootballClub) testClub).setPoints(10);
        int expectedPoints = 10;
        assertEquals(expectedPoints, ((FootballClub) testClub).getPoints());
    }

    @Test
    public void compareTo() {
        SportsClub clubOne = new FootballClub("Arsenal", "Arsenal, United",
                "19/04/2021", "J. F. Kennedy", 5,3,1,4,2);
        SportsClub clubTwo = new FootballClub("Man United", "Manchester, United",
                "02/01/2020", "B. Obama", 5,3,1,4,2);
        int actualTestResult = ((FootballClub)clubOne).compareTo((FootballClub) clubTwo);
        int expectedResult = 0;
        assertEquals(expectedResult, actualTestResult);
        System.out.println("Clubs comparator- Equals passed");

        clubOne = new FootballClub("Arsenal", "Arsenal, United",
                "19/04/2021", "J. F. Kennedy", 5,3,1,4,2);
        clubTwo = new FootballClub("Man United", "Manchester, United",
                "02/01/2020", "B. Obama", 7,3,1,4,2);
        actualTestResult = ((FootballClub)clubOne).compareTo((FootballClub) clubTwo);
        expectedResult = -1;
        assertEquals(expectedResult, actualTestResult);
        System.out.println("Clubs comparator- Less than passed");

        clubOne = new FootballClub("Arsenal", "Arsenal, United",
                "19/04/2021", "J. F. Kennedy", 7,3,1,4,2);
        clubTwo = new FootballClub("Man United", "Manchester, United",
                "02/01/2020", "B. Obama", 5,3,1,4,2);
        actualTestResult = ((FootballClub)clubOne).compareTo((FootballClub) clubTwo);
        expectedResult = 1;
        assertEquals(expectedResult, actualTestResult);
        System.out.println("Clubs comparator- greater than passed");

    }
}
