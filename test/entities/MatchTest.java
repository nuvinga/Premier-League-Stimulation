package entities;

import entities.Football.FootballClub;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MatchTest {

    private SportsClub clubOne = new FootballClub("Man United", "Manchester, United Kingdom",
            "12/2/2020", "J. F. Kennedy", 6,2,1,7,4);
    private SportsClub clubTwo = new FootballClub("Wolves", "Wolverhampton", "05/01/2021",
            "B. Obama");
    private Match testMatch;

    @Before
    public void setUp() throws Exception {
        testMatch = new Match("03/02/2020", clubOne, clubTwo, 4,2);
    }

    @Test
    public void getDate() {
        String actualDate = testMatch.getDate();
        String expectedDate = "03/02/2020";
        assertEquals(expectedDate, actualDate);
        System.out.println("Date get test- passed");
    }

    @Test
    public void comparableDate() throws ParseException {
        Date actualDate = testMatch.comparableDate();
        Date expectedDate = new SimpleDateFormat("dd/MM/yyyy").parse("03/02/2020");
        assertEquals(expectedDate, actualDate);
        System.out.println("Comparable date get test- passed");
    }

    @Test
    public void getTeamOne() {
        String actualTeamOne = testMatch.getTeamOne();
        String expectedTeamOne = "Man United";
        assertEquals(expectedTeamOne, actualTeamOne);
        System.out.println("Team one name test- passed");
    }

    @Test
    public void getTeamTwo() {
        String actualTeamTwo = testMatch.getTeamTwo();
        String expectedTeamTwo = "Wolves";
        assertEquals(expectedTeamTwo, actualTeamTwo);
        System.out.println("Team two name test- passed");
    }

    @Test
    public void getScoreTeamOne() {
        int actualScore = testMatch.getScoreTeamOne();
        int expectedScore = 4;
        assertEquals(expectedScore, actualScore);
        System.out.println("Team one score test- passed");
    }

    @Test
    public void getScoreTeamTwo() {
        int actualScore = testMatch.getScoreTeamTwo();
        int expectedScore = 2;
        assertEquals(expectedScore, actualScore);
        System.out.println("Team two score test- passed");
    }

    @Test
    public void getWinTeam() throws ParseException {
        String actualWinTeam = testMatch.getWinTeam();
        String expectedWinTeam = "Man United- Win";
        assertEquals(expectedWinTeam, actualWinTeam);
        System.out.println("Get win team test- passed");

        testMatch = new Match("04/12/2020", clubOne, clubTwo, 2,2);
        actualWinTeam = testMatch.getWinTeam();
        expectedWinTeam = "Match is a draw";
        assertEquals(expectedWinTeam, actualWinTeam);
        System.out.println("Get draw test- passed");
    }

    @Test
    public void getResult() throws ParseException {
        String actualResult = testMatch.getResult();
        String expectedResult = "one";
        assertEquals(expectedResult, actualResult);
        System.out.println("Get result test- passed");

        testMatch = new Match("04/12/2020", clubOne, clubTwo, 2,2);
        actualResult = testMatch.getResult();
        expectedResult = "draw";
        assertEquals(expectedResult, actualResult);
        System.out.println("Get draw result test- passed");

    }

}
