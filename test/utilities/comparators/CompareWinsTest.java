package utilities.comparators;

import entities.Football.FootballClub;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompareWinsTest {

    CompareWins compareGoals = new CompareWins();

    @Test
    public void compare() {
        FootballClub clubOne = new FootballClub("Arsenal", "Arsenal, United",
                "19/04/2021", "J. F. Kennedy", 5,3,1,4,2);
        FootballClub clubTwo = new FootballClub("Man United", "Manchester, United",
                "02/01/2020", "B. Obama", 5,3,1,4,2);
        int actualTestResult = compareGoals.compare(clubOne, clubTwo);
        int expectedResult = 0;
        assertEquals(expectedResult, actualTestResult);
        System.out.println("Clubs comparator- Equals passed");

        clubOne = new FootballClub("Arsenal", "Arsenal, United",
                "19/04/2021", "J. F. Kennedy", 3,3,1,2,2);
        clubTwo = new FootballClub("Man United", "Manchester, United",
                "02/01/2020", "B. Obama", 5,3,1,4,2);
        actualTestResult = compareGoals.compare(clubOne, clubTwo);
        expectedResult = -1;
        assertEquals(expectedResult, actualTestResult);
        System.out.println("Clubs comparator- Less than passed");

        clubOne = new FootballClub("Arsenal", "Arsenal, United",
                "19/04/2021", "J. F. Kennedy", 7,3,1,6,2);
        clubTwo = new FootballClub("Man United", "Manchester, United",
                "02/01/2020", "B. Obama", 5,3,1,4,2);
        actualTestResult = compareGoals.compare(clubOne, clubTwo);
        expectedResult = 1;
        assertEquals(expectedResult, actualTestResult);
        System.out.println("Clubs comparator- greater than passed");
    }
}
