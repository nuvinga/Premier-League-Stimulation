package services;

import entities.Football.FootballClub;
import entities.SportsClub;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PremierLeagueManagerTest {

    private ArrayList<SportsClub> clubsTestList = new ArrayList<>();
    private SportsClub clubOne;
    private SportsClub clubTwo;

    @Before
    public void setUp() {
        clubOne = new FootballClub("Man United", "Manchester, United Kingdom",
                "12/2/2020", "J. F. Kennedy", 6,2,1,7,4);
        clubTwo = new FootballClub("Wolves", "Wolverhampton", "05/01/2021",
                "B. Obama", 5, 4,2,6,2);
    }

    @Test
    public void addClub() {
        SportsClub expectedClubOne = new FootballClub("Man United", "Manchester, United Kingdom",
                "12/2/2020", "J. F. Kennedy", 6,2,1,7,4);
        SportsClub expectedClubTwo = new FootballClub("Wolves", "Wolverhampton", "05/01/2021",
                "B. Obama", 5, 4,2,6,2);
        int expectedListSize = 2;

        // adding to the test list
        clubsTestList.add(clubOne);
        clubsTestList.add(clubTwo);

        // testing if the clubs are added correctly
        assertEquals(expectedClubOne, clubsTestList.get(0));
        assertEquals(expectedClubTwo, clubsTestList.get(1));
        System.out.println("Addition of clubs test- Successful");

        // testing if clubs are added only twice as intended
        assertEquals(expectedListSize, clubsTestList.size());
        System.out.println("Array list size test- Successful");
    }

    @Test
    public void removeClub() {
        int expectedListSize = 0;
        // removing one club to see if removed as intended
        clubsTestList.remove(clubOne);
        assertEquals(expectedListSize, clubsTestList.size());
    }

}
